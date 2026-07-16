package com.adarsh.backend.feature.auth.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.auth.infrastructure.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    RefreshTokenEntity save(RefreshTokenEntity token);

    Optional<RefreshTokenEntity> findByToken(String token);

    void delete(RefreshTokenEntity token);
}
