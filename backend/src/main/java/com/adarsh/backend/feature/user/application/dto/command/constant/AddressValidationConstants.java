package com.adarsh.backend.feature.user.application.dto.command.constant;

public final class AddressValidationConstants {
    public static final String FULL_NAME_CANNOT_BE_NULL = "Full name cannot be null";
    public static final String ADDRESS_LINE_CANNOT_BE_NULL = "Address line cannot be null";
    public static final String CITY_CANNOT_BE_NULL = "City cannot be null";
    public static final String STATE_CANNOT_BE_NULL = "State cannot be null";
    public static final String PINCODE_CANNOT_BE_NULL = "Pincode cannot be null";
    public static final String COUNTRY_CANNOT_BE_NULL = "Country cannot be null";
    public static final String ADDRESS_TYPE_CANNOT_BE_NULL = "Address type cannot be null";
    public static final String PHONE_CANNOT_BE_NULL = "Phone number cannot be null";

    public static final String INVALID_FULL_NAME = "Full name must contain at least 2 characters";
    public static final String INVALID_ADDRESS_LINE = "Address line must contain at least 5 characters";
    public static final String INVALID_CITY = "City must contain at least 2 characters";
    public static final String INVALID_STATE = "State must contain at least 2 characters";
    public static final String INVALID_PINCODE = "Pincode is invalid";
    public static final String INVALID_COUNTRY = "Country must contain at least 2 characters";
    public static final String INVALID_ADDRESS_TYPE = "Address type cannot be empty";
    public static final String INVALID_PHONE = "Phone number is invalid";

    private AddressValidationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
