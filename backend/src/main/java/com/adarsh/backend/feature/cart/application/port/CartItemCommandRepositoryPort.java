package com.adarsh.backend.feature.cart.application.port;

public interface CartItemCommandRepositoryPort {
    void deleteByCartIdAndCartItemId(Long cartId, Long cartItemId);
}
