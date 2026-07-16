package com.adarsh.backend.feature.vendor.infrastructure.web.exception.constant;

public final class VendorExceptionHandlerLogConstants {

    public static final String APPLICATION_NOT_FOUND = "Vendor application not found.";
    public static final String APPLICATION_ALREADY_EXISTS = "Vendor application already exists.";

    private VendorExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
