package com.adarsh.backend.feature.user.infrastructure.persistence.jparepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.backend.feature.user.infrastructure.persistence.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);

    boolean existsByEmail(String email);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);
}
