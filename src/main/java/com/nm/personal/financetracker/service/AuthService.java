package com.nm.personal.financetracker.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.nm.personal.financetracker.dto.AuthenticationResponseDto;
import com.nm.personal.financetracker.model.User;

public interface AuthService {
 
    ResponseEntity<?> authenticate(User user);

    ResponseEntity<?> register(User user);

    AuthenticationResponseDto refreshToken(UUID refreshToken);

    void revokeRefreshToken(UUID refreshToken);
}
