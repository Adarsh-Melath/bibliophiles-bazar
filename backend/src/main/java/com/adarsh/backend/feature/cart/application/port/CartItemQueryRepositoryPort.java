package com.adarsh.backend.feature.cart.application.port;

import com.adarsh.backend.feature.cart.domain.model.CartItem;

import java.util.Optional;

public interface CartItemQueryRepositoryPort {
    Optional<CartItem> findByCartIdAndBookId(Long cartId, Long bookId);

    Optional<CartItem> findByCartItemId(Long cartItemId);
}
