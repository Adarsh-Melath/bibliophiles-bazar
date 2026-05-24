package com.adarsh.backend.feature.auth.application.port;

import java.util.Optional;

import com.adarsh.backend.feature.auth.domain.RefreshToken;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token);

    Optional<RefreshToken> findByToken(String token);

    void delete(RefreshToken token);

    void deleteByEmail(String email);
}
