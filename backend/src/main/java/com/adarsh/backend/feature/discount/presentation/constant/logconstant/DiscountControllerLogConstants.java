package com.adarsh.backend.feature.discount.presentation.constant.logconstant;

public final class DiscountControllerLogConstants {

    // Customer Controller Logs
    public static final String CUSTOMER_APPLY_COUPON_REQUEST = "REST request to apply coupon code={} for email={}";
    public static final String CUSTOMER_APPLY_COUPON_SUCCESS = "REST response: successfully applied coupon code={}";

    public static final String CUSTOMER_REMOVE_COUPON_REQUEST = "REST request to remove coupon for email={}";

    public static final String CUSTOMER_VALIDATE_COUPON_REQUEST = "REST request to validate coupon code={} for subtotal={}";

    public static final String CUSTOMER_GET_ACTIVE_COUPONS_REQUEST = "REST request to fetch active public coupons";

    public static final String CUSTOMER_GET_BOOK_OFFER_REQUEST = "REST request to get active offer for bookId={}";

    // Admin Controller Logs
    public static final String ADMIN_CREATE_COUPON_REQUEST = "REST request for admin create coupon code={}";
    public static final String ADMIN_UPDATE_COUPON_REQUEST = "REST request for admin update coupon code={}";
    public static final String ADMIN_DELETE_COUPON_REQUEST = "REST request for admin delete coupon code={}";
    public static final String ADMIN_GET_COUPON_REQUEST = "REST request for admin get coupon code={}";
    public static final String ADMIN_SEARCH_COUPONS_REQUEST = "REST request for admin search coupons: keyword={}, active={}, page={}, size={}";

    public static final String ADMIN_CREATE_CATEGORY_OFFER_REQUEST = "REST request for admin create category offer for categoryId={}";
    public static final String ADMIN_CREATE_PRODUCT_OFFER_REQUEST = "REST request for admin create product offer for bookId={}";
    public static final String ADMIN_UPDATE_OFFER_REQUEST = "REST request for admin update offer id={}";
    public static final String ADMIN_DEACTIVATE_OFFER_REQUEST = "REST request for admin deactivate offer id={}";
    public static final String ADMIN_DELETE_OFFER_REQUEST = "REST request for admin delete offer id={}";
    public static final String ADMIN_SEARCH_OFFERS_REQUEST = "REST request for admin search offers: keyword={}, type={}, page={}, size={}";

    // Vendor Controller Logs
    public static final String VENDOR_CREATE_OFFER_REQUEST = "REST request for vendor create offer by email={}, bookId={}";
    public static final String VENDOR_UPDATE_OFFER_REQUEST = "REST request for vendor update offer id={} by email={}";
    public static final String VENDOR_DEACTIVATE_OFFER_REQUEST = "REST request for vendor deactivate offer id={} by email={}";
    public static final String VENDOR_DELETE_OFFER_REQUEST = "REST request for vendor delete offer id={} by email={}";
    public static final String VENDOR_SEARCH_OFFERS_REQUEST = "REST request for vendor search offers by email={}, page={}, size={}";

    private DiscountControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
