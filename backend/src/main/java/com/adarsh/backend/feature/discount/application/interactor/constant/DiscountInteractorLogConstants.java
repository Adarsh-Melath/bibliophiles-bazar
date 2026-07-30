package com.adarsh.backend.feature.discount.application.interactor.constant;

public final class DiscountInteractorLogConstants {

    // Coupon logs
    public static final String CREATE_COUPON_REQUEST = "Request to create coupon code={}";
    public static final String CREATE_COUPON_SUCCESS = "Successfully created coupon code={}";

    public static final String UPDATE_COUPON_REQUEST = "Request to update coupon code={}";
    public static final String UPDATE_COUPON_SUCCESS = "Successfully updated coupon code={}";

    public static final String DELETE_COUPON_REQUEST = "Request to delete coupon code={}";
    public static final String DELETE_COUPON_SUCCESS = "Successfully deleted coupon code={}";

    public static final String GET_COUPON_REQUEST = "Request to fetch coupon code={}";
    public static final String SEARCH_COUPONS_REQUEST = "Request to search coupons with keyword={}, active={}, page={}, size={}";

    public static final String APPLY_COUPON_REQUEST = "Request to apply coupon code={} for user email={}";
    public static final String APPLY_COUPON_SUCCESS = "Successfully applied coupon code={} for user email={}";

    public static final String VALIDATE_COUPON_REQUEST = "Request to validate coupon code={} for subtotal={}";

    // Offer logs
    public static final String CREATE_PRODUCT_OFFER_REQUEST = "Request to create product offer for bookId={} by email={}";
    public static final String CREATE_CATEGORY_OFFER_REQUEST = "Request to create category offer for categoryId={}";

    public static final String UPDATE_OFFER_REQUEST = "Request to update offer id={} by email={}";
    public static final String UPDATE_OFFER_SUCCESS = "Successfully updated offer id={}";

    public static final String DEACTIVATE_OFFER_REQUEST = "Request to deactivate offer id={} by email={}";
    public static final String DEACTIVATE_OFFER_SUCCESS = "Successfully deactivated offer id={}";

    public static final String DELETE_OFFER_REQUEST = "Request to delete offer id={} by email={}";
    public static final String DELETE_OFFER_SUCCESS = "Successfully deleted offer id={}";

    public static final String SEARCH_OFFERS_REQUEST = "Request to search offers: keyword={}, type={}, publisherId={}, page={}, size={}";
    public static final String GET_ACTIVE_OFFER_FOR_BOOK_REQUEST = "Request to get active offer for bookId={}";

    private DiscountInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
