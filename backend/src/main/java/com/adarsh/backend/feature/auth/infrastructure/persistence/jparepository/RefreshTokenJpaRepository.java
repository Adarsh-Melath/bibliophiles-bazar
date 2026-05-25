package com.adarsh.backend.feature.auth.infrastructure.persistence.jparepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.backend.feature.auth.infrastructure.persistence.entity.RefreshTokenEntity;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    RefreshTokenEntity save(RefreshTokenEntity token);

    Optional<RefreshTokenEntity> findByToken(String token);

    void delete(RefreshTokenEntity token);

    void deleteByEmail(String email);
}
