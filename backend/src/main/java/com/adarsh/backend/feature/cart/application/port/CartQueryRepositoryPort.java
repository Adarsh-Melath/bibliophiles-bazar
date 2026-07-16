package com.adarsh.backend.feature.cart.application.port;

import com.adarsh.backend.feature.cart.domain.model.Cart;

import java.util.Optional;

public interface CartQueryRepositoryPort {
    Optional<Cart> findByUserId(Long userId);
}
