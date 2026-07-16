package com.adarsh.backend.feature.cart.application.dto.command.constant;

public final class CartValidationConstants {
    public static final String SLUG_CANNOT_BE_NULL = "Slug must not be null";
    public static final String INVALID_QUANTITY = "quantity must be greater than 0";
    public static final int MAX_QUANTITY_LIMIT = 5;
    public static final String MAX_QUANTITY_EXCEEDED = "Quantity exceeds the maximum allowed limit of 5 units per item";

    private CartValidationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
