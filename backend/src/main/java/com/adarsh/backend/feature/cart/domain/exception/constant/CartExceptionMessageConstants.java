package com.adarsh.backend.feature.cart.domain.exception.constant;

public final class CartExceptionMessageConstants {

    public static final String CART_ITEM_NOT_FOUND =
            "Cart item not found";
    public static final String INSUFFICIENT_STOCK =
            "Cannot add quantity. Total requested units (%d) exceeds available stock (%d).";
    public static final String INVALID_QUANTITY =
            "Quantity must be greater than zero.";
    public static final int MAX_QUANTITY_LIMIT = 5;
    public static final String MAX_QUANTITY_EXCEEDED =
            "Quantity exceeds the maximum allowed limit of %d units per item.";

    private CartExceptionMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
