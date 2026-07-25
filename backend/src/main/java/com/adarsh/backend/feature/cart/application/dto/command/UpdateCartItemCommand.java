package com.adarsh.backend.feature.cart.application.dto.command;

import com.adarsh.backend.feature.cart.application.dto.command.constant.CartValidationConstants;
import java.util.Objects;

public record UpdateCartItemCommand(Long cartItemId, int quantity) {
    public UpdateCartItemCommand {
        Objects.requireNonNull(cartItemId, CartValidationConstants.CART_ITEM_ID_CANNOT_BE_NULL);

        if (quantity <= 0) {
            throw new IllegalArgumentException(CartValidationConstants.INVALID_QUANTITY);
        }

        if (quantity > CartValidationConstants.MAX_QUANTITY_LIMIT) {
            throw new IllegalArgumentException(CartValidationConstants.MAX_QUANTITY_EXCEEDED);
        }
    }
}
