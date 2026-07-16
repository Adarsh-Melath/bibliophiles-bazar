package com.adarsh.backend.feature.cart.infrastructure.web.exception.constant;

public final class CartExceptionHandlerLogConstants {

    public static final String CART_ITEM_NOT_FOUND =
            "Cart item not found exception occurred";
    public static final String INSUFFICIENT_STOCK =
            "Insufficient stock exception occurred";
    public static final String QUANTITY_LIMIT_EXCEEDED =
            "Cart item quantity limit exceeded exception occurred";

    private CartExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
