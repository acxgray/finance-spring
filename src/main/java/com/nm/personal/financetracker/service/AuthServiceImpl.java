package com.nm.personal.financetracker.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nm.personal.financetracker.dto.AuthenticationResponseDto;
import com.nm.personal.financetracker.model.RefreshToken;
import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.repository.RefreshTokenRepository;
import com.nm.personal.financetracker.repository.UserRepository;
import com.nm.personal.financetracker.utils.JwtUtil;

import jakarta.validation.ValidationException;

@Service
public class AuthServiceImpl implements AuthService {

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

        User userDetail = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        RefreshToken refreshToken = new RefreshToken();

        Duration timeToAdd = Duration.ofHours(72);

        refreshToken.setUser_refresh(userDetail);
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setExpiresAt(Instant.now().plus(timeToAdd));
        refreshTokenRepository.save(refreshToken);

        return new ResponseEntity<>(
                new AuthenticationResponseDto(jwtUtils.generateToken(userDetails.getUsername()), refreshToken.getId()),
                HttpStatus.OK);
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
    public AuthenticationResponseDto refreshToken(UUID refreshToken) {
        final var refreshTokenEntity = refreshTokenRepository.findByIdAndExpiresAtAfter(refreshToken, Instant.now())
                .orElseThrow(() -> new ValidationException(
                        "ERROR: " + Map.of("refreshToken", "Invalid or expired refresh token")));

        final var newAccessToken = jwtUtils
                .generateToken(refreshTokenEntity.getUser_refresh().getUsername());
        return new AuthenticationResponseDto(newAccessToken, refreshToken);
    }

    @Override
    public void revokeRefreshToken(UUID refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }

}
