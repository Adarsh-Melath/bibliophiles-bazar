package com.adarsh.backend.feature.discount.presentation.constant.apiconstant;

public final class DiscountControllerApiConstants {

    public static final String CUSTOMER_DISCOUNTS_BASE_PATH = "/api/v1/discounts";
    public static final String ADMIN_COUPONS_BASE_PATH = "/api/v1/admin/discounts/coupons";
    public static final String ADMIN_OFFERS_BASE_PATH = "/api/v1/admin/discounts/offers";
    public static final String VENDOR_OFFERS_BASE_PATH = "/api/v1/vendor/discounts/offers";

    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";

    private DiscountControllerApiConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
