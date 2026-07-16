package com.adarsh.backend.feature.auth.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshTokenGenerator {
    private RefreshTokenGenerator() {
    }

    public static RefreshToken generateRefreshToken(String email) {
        return new RefreshToken.Builder().token(UUID.randomUUID().toString()).email(email)
                .expiresAt(LocalDateTime.now().plusDays(7)).build();
    }
}
