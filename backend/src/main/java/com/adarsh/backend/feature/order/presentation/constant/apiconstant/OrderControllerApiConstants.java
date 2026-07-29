package com.adarsh.backend.feature.order.presentation.constant.apiconstant;

public final class OrderControllerApiConstants {

    // Customer / Admin / Vendor order search
    public static final String CUSTOMER_ORDERS_BASE_PATH = "/api/v1/orders";
    public static final String ADMIN_ORDERS_BASE_PATH = "/api/v1/admin/orders";
    public static final String VENDOR_ORDERS_BASE_PATH = "/api/v1/vendor/orders";

    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_OPTION = "NEWEST";

    // Invoice
    public static final String INVOICE_BASE_PATH = "/api/invoices";
    public static final String GET_INVOICE_PATH = "/{orderId}/invoice";
    public static final String INVOICE_ATTACHMENT = "attachment";
    public static final String INVOICE_FILENAME_PREFIX = "invoice-";
    public static final String INVOICE_FILE_EXTENSION = ".pdf";

    private OrderControllerApiConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
