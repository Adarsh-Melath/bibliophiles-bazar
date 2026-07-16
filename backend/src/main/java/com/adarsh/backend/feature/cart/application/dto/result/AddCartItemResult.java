package com.adarsh.backend.feature.cart.application.dto.result;

import com.adarsh.backend.feature.cart.domain.model.CartItem;

public record AddCartItemResult(Long id, Long cartId, Long bookId, int quantity, Double unitPrice) {
    public static AddCartItemResult fromDomain(CartItem item) {
        return new AddCartItemResult(item.getId(), item.getCartId(), item.getBookId(), item.getQuantity(), item.getUnitPrice());
    }
}
