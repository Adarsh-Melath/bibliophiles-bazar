package com.adarsh.backend.feature.cart.application.port;

import com.adarsh.backend.feature.cart.domain.model.Cart;

public interface CartCommandRepositoryPort {
    Cart save(Cart cart);
}
