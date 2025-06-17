package com.nm.personal.financetracker.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nm.personal.financetracker.model.RefreshToken;
import com.nm.personal.financetracker.model.User;

public interface AuthService {
 
    ResponseEntity<?> authenticate(User user);

    ResponseEntity<?> register(User user);

    RefreshToken createRefreshToken(Long userId);

    boolean isTokenExpired(RefreshToken token);

    ResponseEntity<?> refreshToken(Map<String, String> payload);

    ResponseEntity<?> logout(Map<String, String> payload);
}
