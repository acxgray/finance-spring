package com.nm.personal.financetracker.service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nm.personal.financetracker.model.RefreshToken;
import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.repository.RefreshTokenRepository;
import com.nm.personal.financetracker.repository.UserRepository;
import com.nm.personal.financetracker.utils.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    public ResponseEntity<?> authenticate(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateToken(userDetails.getUsername());

        User dbUser = userRepository.getByUsername(userDetails.getUsername());
        RefreshToken refreshToken = createRefreshToken(dbUser.getId());

        return new ResponseEntity<>(Map.of("accessToken", accessToken, "refreshToken", refreshToken), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }
        // Create new user's account
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.BAD_REQUEST);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        var token = new RefreshToken();
        token.setUser_refresh(userRepository.findById(userId).get());
        token.setCreatedAt(Instant.now());
        token.setExpiresAt(Instant.now().plusMillis(refreshTokenDurationMs));
        token.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(token);
    }

    @Override
    public boolean isTokenExpired(RefreshToken token) {
        return token.getExpiresAt().isBefore(Instant.now());
    }

    @Override
    public ResponseEntity<?> refreshToken(Map<String, String> payload) {
        String requestToken = payload.get("refreshToken");

        return refreshTokenRepository.findByToken(requestToken).map(
                token -> {
                    if (isTokenExpired(token)) {
                        refreshTokenRepository.delete(token);
                        return new ResponseEntity<>("Refresh token expired. Please login again.",
                                HttpStatus.BAD_REQUEST);
                    }
                    String newJwt = jwtUtils.generateToken(token.getUser_refresh().getUsername());
                    return new ResponseEntity<>(Map.of("token", newJwt), HttpStatus.OK);
                }).orElse(new ResponseEntity<>("Invalid refresh token", HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<?> logout(Map<String, String> payload) {
        String refreshToken = payload.get("refreshToken");

        if (refreshToken == null || refreshToken.isBlank()) {
            return new ResponseEntity<>("Refresh Token is required", HttpStatus.BAD_REQUEST);
        }

        return refreshTokenRepository.findByToken(refreshToken).map(
            token -> {
                refreshTokenRepository.delete(token);
                return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
            }
        ).orElse(new ResponseEntity<>("Invalid Refresh Token", HttpStatus.BAD_REQUEST));
    }

}
