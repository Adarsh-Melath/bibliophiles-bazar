package com.adarsh.backend.feature.cart.application.dto.result;

import com.adarsh.backend.feature.cart.domain.model.CartItem;

public record UpdateCartItemResult(Long id, Long cartId, Long bookId, int quantity,
                                   Double unitPrice) {
    public static UpdateCartItemResult fromDomain(CartItem cartItem) {
        return new UpdateCartItemResult(cartItem.getId(), cartItem.getCartId(), cartItem.getBookId(), cartItem.getQuantity(), cartItem.getUnitPrice());
    }
}
