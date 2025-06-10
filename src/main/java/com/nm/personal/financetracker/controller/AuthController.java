package com.nm.personal.financetracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nm.personal.financetracker.dto.AuthenticationResponseDto;
import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        return authService.authenticate(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam UUID refreshToken) {
        AuthenticationResponseDto response = 
          authService.refreshToken(refreshToken);
    return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> revokeRefreshToken(@RequestParam UUID refreshToken) {
        authService.revokeRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }

}
