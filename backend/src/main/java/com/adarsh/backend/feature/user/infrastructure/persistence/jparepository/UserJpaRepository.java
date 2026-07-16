package com.adarsh.backend.feature.user.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);

    boolean existsByEmailAndDeletedFalse(String email);

    Optional<UserEntity> findByIdAndDeletedFalse(Long id);

    Optional<UserEntity> findByEmailAndDeletedFalse(String email);

    @Query("SELECT u FROM UserEntity u WHERE " + "u.deleted = false " + "AND (:keyword IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " + "AND (:role IS NULL OR u.role = :role) " + "AND (:enabled IS NULL OR u.enabled = :enabled)")
    Page<UserEntity> searchUsersAndDeletedFalse(@Param("keyword") String keyword, @Param("role") Role role, @Param("enabled") Boolean enabled, Pageable pageable);
}
