package com.adarsh.backend.feature.order.presentation.constant.logconstant;

public final class OrderControllerLogConstants {

    // Customer orders
    public static final String CUSTOMER_SEARCH_ORDERS_REQUEST = "REST request to search customer orders for email={}, keyword={}, status={}, page={}, size={}";
    public static final String CUSTOMER_SEARCH_ORDERS_SUCCESS = "Successfully retrieved customer orders for email={}, totalElements={}";

    // Admin orders
    public static final String ADMIN_SEARCH_ORDERS_REQUEST = "REST request for admin order search: keyword={}, status={}, customerId={}, page={}, size={}";
    public static final String ADMIN_SEARCH_ORDERS_SUCCESS = "Successfully retrieved admin order search results, totalElements={}";

    // Vendor orders
    public static final String VENDOR_SEARCH_ORDERS_REQUEST = "REST request for vendor order search for email={}, itemStatus={}, page={}, size={}";
    public static final String VENDOR_SEARCH_ORDERS_SUCCESS = "Successfully retrieved vendor order search results for email={}, totalElements={}";

    // Invoice
    public static final String INVOICE_REQUEST = "REST request to generate invoice for order id={} by user email={}";

    private OrderControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
