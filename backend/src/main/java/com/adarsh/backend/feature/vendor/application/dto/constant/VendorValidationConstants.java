package com.adarsh.backend.feature.vendor.application.dto.constant;

public final class VendorValidationConstants {

    // Name
    public static final String NAME_CANNOT_BE_NULL = "Name cannot be null";

    public static final String INVALID_NAME = "Name must contain at least 2 characters";

    // Email
    public static final String EMAIL_CANNOT_BE_NULL = "Email cannot be null";

    public static final String INVALID_EMAIL = "Email address is invalid";

    // Phone
    public static final String PHONE_CANNOT_BE_NULL = "Phone number cannot be null";

    public static final String INVALID_PHONE = "Phone number is invalid";

    // Business Name
    public static final String BUSINESS_NAME_CANNOT_BE_NULL = "Business name cannot be null";

    public static final String INVALID_BUSINESS_NAME = "Business name must contain at least 2 characters";

    // Business Description
    public static final String BUSINESS_DESCRIPTION_CANNOT_BE_NULL = "Business description cannot be null";

    public static final String INVALID_BUSINESS_DESCRIPTION = "Business description must contain at least 10 characters";

    // Category
    public static final String CATEGORY_CANNOT_BE_NULL = "Category cannot be null";

    public static final String INVALID_CATEGORY = "Category cannot be empty";

    // Registration Number
    public static final String BUSINESS_REGISTRATION_NUMBER_CANNOT_BE_NULL = "Business registration number cannot be null";

    public static final String INVALID_BUSINESS_REGISTRATION_NUMBER = "Business registration number cannot be empty";

    // Rejection Reason
    public static final String REJECTION_REASON_CANNOT_BE_NULL = "Rejection reason cannot be null";

    public static final String INVALID_REJECTION_REASON = "Rejection reason must contain at least 5 characters";

    private VendorValidationConstants() {

    }
}