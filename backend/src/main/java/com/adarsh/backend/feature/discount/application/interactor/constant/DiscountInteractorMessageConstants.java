package com.adarsh.backend.feature.discount.application.interactor.constant;

/**
 * Message constants used by discount interactors.
 * Contains user‑facing template strings that were previously hard‑coded.
 */
public final class DiscountInteractorMessageConstants {

    private DiscountInteractorMessageConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Default name prefix for a category offer when a custom name is not provided.
     * The placeholder will be replaced with the category display name.
     */
    public static final String DEFAULT_CATEGORY_OFFER_NAME = "Category Offer - %s";

    /**
     * Default name prefix for a product offer when a custom name is not provided.
     * The placeholder will be replaced with the book title.
     */
    public static final String DEFAULT_PRODUCT_OFFER_NAME = "Product Offer - %s";

    /**
     * Error message when attempting to apply a coupon to an empty cart.
     */
    public static final String EMPTY_CART_COUPON_ERROR = "Cannot apply coupon to an empty cart.";

    /**
     * Error message when a coupon is inactive, expired, or minimum purchase criteria isn't met.
     */
    public static final String INAPPLICABLE_COUPON_ERROR = "Cart does not meet the requirements for this coupon (Expired, Inactive, or Minimum Order Value not met).";

    /**
     * Success message returned when a coupon is successfully applied.
     */
    public static final String COUPON_APPLY_SUCCESS = "Coupon applied successfully!";

    /**
     * Success message returned when a coupon is successfully removed from the cart.
     */
    public static final String COUPON_REMOVE_SUCCESS = "Coupon removed successfully.";

    /**
     * Error message when the coupon code fails validation regex.
     */
    public static final String COUPON_CODE_INVALID_FORMAT = "Coupon code must be strictly alphanumeric (letters, numbers, hyphens, underscores allowed).";

    /**
     * Error message when the cart subtotal is zero or negative during coupon validation.
     */
    public static final String INVALID_SUBTOTAL = "Subtotal must be greater than zero.";

    /**
     * Error message when cart subtotal doesn't meet coupon specifications.
     */
    public static final String SUBTOTAL_REQUIREMENT_NOT_MET = "Cart subtotal does not meet requirements for this coupon.";

    /**
     * Success message when a coupon is valid.
     */
    public static final String COUPON_VALID = "Coupon is valid.";
}
