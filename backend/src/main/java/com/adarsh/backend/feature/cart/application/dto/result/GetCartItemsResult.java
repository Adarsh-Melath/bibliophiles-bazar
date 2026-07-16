package com.adarsh.backend.feature.cart.application.dto.result;

import com.adarsh.backend.feature.cart.domain.model.CartItem;

public record GetCartItemsResult(Long id, Long cartId, Long bookId, int quantity, Double unitPrice,
                                 Double totalPrice) {
    public static GetCartItemsResult fromDomain(CartItem item) {
        Double totalPrice = item.getUnitPrice() * item.getQuantity();
        return new GetCartItemsResult(item.getId(), item.getCartId(), item.getBookId(), item.getQuantity(), item.getUnitPrice(), totalPrice);
    }
}
