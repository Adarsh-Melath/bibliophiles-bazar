package com.adarsh.backend.feature.discount.domain.exception.constant;

public final class DiscountExceptionMessageConstants {

    public static final String COUPON_NOT_FOUND = "Coupon with code '%s' was not found.";
    public static final String COUPON_ALREADY_EXISTS = "A coupon with code '%s' already exists.";
    public static final String COUPON_EXPIRED = "Coupon '%s' has expired.";
    public static final String INVALID_COUPON = "Invalid coupon: %s";

    public static final String OFFER_NOT_FOUND = "Offer with id '%d' was not found.";
    public static final String INVALID_OFFER = "Invalid offer: %s";
    public static final String UNAUTHORIZED_OFFER_ACCESS = "You are not authorized to modify or access this offer.";
    public static final String OFFER_ALREADY_EXISTS = "An active offer already exists for this target.";

    private DiscountExceptionMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
