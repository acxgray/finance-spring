package com.nm.personal.financetracker.dto;

import java.util.UUID;

public record AuthenticationResponseDto(String accessToken, UUID refreshToken) {
}
