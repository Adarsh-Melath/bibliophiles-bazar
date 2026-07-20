package com.adarsh.backend.feature.wishlist.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.wishlist.infrastructure.persistence.entity.WishlistItemEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistItemJpaRepository extends JpaRepository<WishlistItemEntity, Long> {

    @NullMarked
    Optional<WishlistItemEntity> findById(Long id);

    @Modifying
    @Query("DELETE FROM WishlistItemEntity wi WHERE wi.wishlist.id = :wishlistId AND wi.id = :wishlistItemId")
    void deleteByWishlistIdAndWishlistItemId(@Param("wishlistId") Long wishlistId, @Param("wishlistItemId") Long wishlistItemId);
}
