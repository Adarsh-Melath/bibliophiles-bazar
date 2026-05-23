package com.adarsh.backend.shared.application.port;

import java.util.Optional;

import com.adarsh.backend.shared.domain.RefreshToken;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token);

    Optional<RefreshToken> findByToken(String token);

    void delete(RefreshToken token);

    void deleteByEmail(String email);
}
