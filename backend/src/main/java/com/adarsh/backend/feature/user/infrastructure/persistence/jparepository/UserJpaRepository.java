package com.adarsh.backend.feature.user.infrastructure.persistence.jparepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);

    boolean existsByEmail(String email);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findAll(Pageable pageable);


    @Query("SELECT u FROM UserEntity u WHERE " +
           "(:keyword IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:role IS NULL OR u.role = :role) " +
           "AND (:enabled IS NULL OR u.enabled = :enabled)")
    Page<UserEntity> searchUsers(
            @Param("keyword") String keyword, 
            @Param("role") Role role, 
            @Param("enabled") Boolean enabled, 
            Pageable pageable);
}
