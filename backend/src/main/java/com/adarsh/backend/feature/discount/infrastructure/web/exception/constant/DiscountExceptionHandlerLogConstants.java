package com.adarsh.backend.feature.discount.infrastructure.web.exception.constant;

public final class DiscountExceptionHandlerLogConstants {

    public static final String COUPON_NOT_FOUND = "Coupon not found exception handled.";
    public static final String COUPON_ALREADY_EXISTS = "Coupon already exists exception handled.";
    public static final String INVALID_COUPON = "Invalid coupon exception handled.";
    public static final String COUPON_EXPIRED = "Coupon expired exception handled.";
    public static final String OFFER_NOT_FOUND = "Offer not found exception handled.";
    public static final String INVALID_OFFER = "Invalid offer exception handled.";
    public static final String UNAUTHORIZED_OFFER_ACCESS = "Unauthorized offer access exception handled.";

    private DiscountExceptionHandlerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
