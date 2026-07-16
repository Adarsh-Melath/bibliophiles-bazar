package com.adarsh.backend.feature.auth.application.port;

import com.adarsh.backend.feature.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token);

    Optional<RefreshToken> findByToken(String token);
}
