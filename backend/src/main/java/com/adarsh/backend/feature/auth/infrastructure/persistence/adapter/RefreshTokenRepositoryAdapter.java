package com.adarsh.backend.feature.auth.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adarsh.backend.feature.auth.application.port.RefreshTokenRepository;
import com.adarsh.backend.feature.auth.domain.RefreshToken;
import com.adarsh.backend.feature.auth.infrastructure.persistence.jparepository.RefreshTokenJpaRepository;
import com.adarsh.backend.feature.auth.infrastructure.persistence.mapper.RefreshTokenPersistenceMapper;
import com.adarsh.backend.feature.auth.infrastructure.persistence.entity.RefreshTokenEntity;

import lombok.RequiredArgsConstructor;

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

    @Override
    public void delete(RefreshToken token) {
        refreshTokenJpaRepository.delete(refreshTokenPersistenceMapper.toEntity(token));
    }

    @Override
    public void deleteByEmail(String email) {
        refreshTokenJpaRepository.deleteByEmail(email);
    }
}
