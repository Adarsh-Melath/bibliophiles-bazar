package com.adarsh.backend.feature.wishlist.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.wishlist.infrastructure.persistence.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistJpaRepository extends JpaRepository<WishlistEntity, Long> {
    Optional<WishlistEntity> findByUserId(Long userId);
}
