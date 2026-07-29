package com.adarsh.backend.feature.order.infrastructure.persistence.constant;

public final class OrderPersistenceConstants {

    // OrderEntity field names (must match entity field names exactly)
    public static final String FIELD_CUSTOMER_ID = "customerId";
    public static final String FIELD_ORDER_STATUS = "orderStatus";
    public static final String FIELD_PAYMENT_STATUS = "paymentStatus";
    public static final String FIELD_PAYMENT_METHOD = "paymentMethod";
    public static final String FIELD_CREATED_AT = "createdAt";
    public static final String FIELD_GRAND_TOTAL = "grandTotal";
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_SHIPPING_FULL_NAME = "shippingFullName";
    public static final String FIELD_SHIPPING_CITY = "shippingCity";

    // OrderEntity association name (must match entity field names exactly)
    public static final String FIELD_ITEMS = "items";

    // OrderItemEntity field names (must match entity field names exactly)
    public static final String FIELD_BOOK_ID = "bookId";
    public static final String FIELD_ITEM_STATUS = "status";
    public static final String FIELD_BOOK_TITLE = "bookTitle";
    public static final String FIELD_ISBN = "isbn";

    // Sort field aliases (used by OrderRepositoryAdapter.toSpringSort)
    public static final String SORT_FIELD_CREATED_AT = FIELD_CREATED_AT;
    public static final String SORT_FIELD_GRAND_TOTAL = FIELD_GRAND_TOTAL;

    private OrderPersistenceConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
