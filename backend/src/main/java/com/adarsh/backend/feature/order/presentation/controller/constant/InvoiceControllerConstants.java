package com.adarsh.backend.feature.order.presentation.controller.constant;

public final class InvoiceControllerConstants {
    public static final String API_PATH = "/api/invoices";
    public static final String GET_INVOICE_PATH = "/{orderId}/invoice";
    public static final String ATTACHMENT = "attachment";
    public static final String FILENAME_PREFIX = "invoice-";
    public static final String FILE_EXTENSION = ".pdf";
    public static final String LOG_REQUEST = "REST request to generate invoice for order id={} by user email={}";

    private InvoiceControllerConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
