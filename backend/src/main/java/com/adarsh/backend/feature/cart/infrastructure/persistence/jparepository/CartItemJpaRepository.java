package com.adarsh.backend.feature.cart.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.cart.infrastructure.persistence.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {
    
    @Query("SELECT ci FROM CartItemEntity ci WHERE ci.cart.id = :cartId AND ci.bookId = :bookId")
    Optional<CartItemEntity> findByCartIdAndBookId(@Param("cartId") Long cartId, @Param("bookId") Long bookId);
    
    Optional<CartItemEntity> findById(Long id);
    
    @Modifying
    @Query("DELETE FROM CartItemEntity ci WHERE ci.cart.id = :cartId AND ci.id = :cartItemId")
    void deleteByCartIdAndCartItemId(@Param("cartId") Long cartId, @Param("cartItemId") Long cartItemId);
}
