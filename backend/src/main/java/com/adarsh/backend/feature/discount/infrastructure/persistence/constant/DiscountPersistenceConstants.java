package com.adarsh.backend.feature.discount.infrastructure.persistence.constant;

public final class DiscountPersistenceConstants {

    // Coupon field names
    public static final String COUPON_FIELD_CODE = "code";
    public static final String COUPON_FIELD_DISCOUNT_TYPE = "discountType";
    public static final String COUPON_FIELD_IS_ACTIVE = "isActive";

    // Offer field names
    public static final String OFFER_FIELD_ID = "id";
    public static final String OFFER_FIELD_NAME = "name";
    public static final String OFFER_FIELD_OFFER_TYPE = "offerType";
    public static final String OFFER_FIELD_TARGET_ID = "targetId";
    public static final String OFFER_FIELD_PUBLISHER_ID = "publisherId";
    public static final String OFFER_FIELD_IS_ACTIVE = "isActive";
    public static final String OFFER_FIELD_START_DATE = "startDate";
    public static final String OFFER_FIELD_END_DATE = "endDate";

    private DiscountPersistenceConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
