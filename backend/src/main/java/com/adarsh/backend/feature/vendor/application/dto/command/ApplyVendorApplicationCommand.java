package com.adarsh.backend.feature.vendor.application.dto.command;

import com.adarsh.backend.feature.vendor.application.dto.constant.VendorValidationConstants;

import java.util.Objects;

public record ApplyVendorApplicationCommand(String name, String email, String phone,
                                            String businessName, String businessDescription,
                                            String category, String businessRegistrationNumber,
                                            String website, String publishingSince) {

    public ApplyVendorApplicationCommand {
        Objects.requireNonNull(name, VendorValidationConstants.NAME_CANNOT_BE_NULL);
        Objects.requireNonNull(email, VendorValidationConstants.EMAIL_CANNOT_BE_NULL);
        Objects.requireNonNull(phone, VendorValidationConstants.PHONE_CANNOT_BE_NULL);
        Objects.requireNonNull(businessName, VendorValidationConstants.BUSINESS_NAME_CANNOT_BE_NULL);
        Objects.requireNonNull(businessDescription, VendorValidationConstants.BUSINESS_DESCRIPTION_CANNOT_BE_NULL);
        Objects.requireNonNull(category, VendorValidationConstants.CATEGORY_CANNOT_BE_NULL);
        Objects.requireNonNull(businessRegistrationNumber, VendorValidationConstants.BUSINESS_REGISTRATION_NUMBER_CANNOT_BE_NULL);

        if (name.isBlank() || name.length() < 2) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_NAME);
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_EMAIL);
        }
        if (!phone.matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_PHONE);
        }
        if (businessName.isBlank() || businessName.length() < 2) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_BUSINESS_NAME);
        }
        if (businessDescription.isBlank() || businessDescription.length() < 10) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_BUSINESS_DESCRIPTION);
        }
        if (category.isBlank()) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_CATEGORY);
        }
        if (businessRegistrationNumber.isBlank()) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_BUSINESS_REGISTRATION_NUMBER);
        }
    }
}
