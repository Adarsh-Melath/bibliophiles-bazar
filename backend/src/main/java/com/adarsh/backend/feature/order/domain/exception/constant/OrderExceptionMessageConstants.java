package com.adarsh.backend.feature.order.domain.exception.constant;

public final class OrderExceptionMessageConstants {
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_ITEM_NOT_FOUND = "Order item not found";
    public static final String INVALID_ORDER_STATE = "Order has already been shipped or processed and cannot be cancelled.";
    public static final String ORDER_NOT_FOUND_FOR_USER = "Order not found for user: %d and order: %d";
    public static final String RETURNS_ONLY_FOR_DELIVERED = "Returns can only be initiated for delivered orders.";
    public static final String FAILED_TO_GENERATE_INVOICE = "Failed to generate PDF invoice";

    private OrderExceptionMessageConstants() {
    }
}
