package com.adarsh.backend.feature.cart.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.cart.infrastructure.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByUserId(Long userId);
}
