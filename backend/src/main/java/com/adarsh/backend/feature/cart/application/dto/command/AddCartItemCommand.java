package com.adarsh.backend.feature.cart.application.dto.command;

import com.adarsh.backend.feature.cart.application.dto.command.constant.CartValidationConstants;

import java.util.Objects;

public record AddCartItemCommand(String slug, int quantity) {

    public AddCartItemCommand {
        Objects.requireNonNull(slug, CartValidationConstants.SLUG_CANNOT_BE_NULL);

        if (quantity <= 0) {
            throw new IllegalArgumentException(CartValidationConstants.INVALID_QUANTITY);
        }

        if (quantity > CartValidationConstants.MAX_QUANTITY_LIMIT) {
            throw new IllegalArgumentException(CartValidationConstants.MAX_QUANTITY_EXCEEDED);
        }
    }
}