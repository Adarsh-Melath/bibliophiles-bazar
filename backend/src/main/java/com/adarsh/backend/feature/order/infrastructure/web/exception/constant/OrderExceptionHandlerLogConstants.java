package com.adarsh.backend.feature.order.infrastructure.web.exception.constant;

public final class OrderExceptionHandlerLogConstants {

    public static final String ORDER_NOT_FOUND = "Order not found.";
    public static final String ORDER_ITEM_NOT_FOUND = "Order item not found.";
    public static final String INVALID_ORDER_STATE = "Invalid order state.";
    public static final String FAILED_TO_GENERATE_INVOICE = "Failed to generate PDF invoice.";

    private OrderExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
