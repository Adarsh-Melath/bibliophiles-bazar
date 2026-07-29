package com.adarsh.backend.feature.order.infrastructure.pdfgeneration.constant;

public final class InvoicePdfConstants {
    public static final String TEMPLATE_NAME = "invoice";
    public static final String VAR_ORDER = "order";
    public static final String VAR_CUSTOMER_NAME = "customerName";
    
    public static final String LOG_PDF_START = "Starting PDF generation for order id={}, orderNumber={}";
    public static final String LOG_PDF_SUCCESS = "PDF generation completed successfully for order id={}";
    public static final String LOG_PDF_ERROR = "Error occurred while generating PDF for order id={}";

    private InvoicePdfConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
