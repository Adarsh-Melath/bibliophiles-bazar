package com.adarsh.backend.feature.auth.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.auth.application.port.RefreshTokenRepository;
import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.infrastructure.persistence.entity.RefreshTokenEntity;
import com.adarsh.backend.feature.auth.infrastructure.persistence.jparepository.RefreshTokenJpaRepository;
import com.adarsh.backend.feature.auth.infrastructure.persistence.mapper.RefreshTokenPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenPersistenceMapper refreshTokenPersistenceMapper;

    @Override
    public RefreshToken save(RefreshToken token) {
        RefreshTokenEntity savedEntity = refreshTokenJpaRepository.save(refreshTokenPersistenceMapper.toEntity(token));

        return refreshTokenPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token).map(refreshTokenPersistenceMapper::toDomain);
    }
}
