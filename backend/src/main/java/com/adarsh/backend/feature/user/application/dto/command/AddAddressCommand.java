package com.adarsh.backend.feature.user.application.dto.command;

import com.adarsh.backend.feature.user.application.dto.command.constant.AddressValidationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record AddAddressCommand(String fullName, String phone, String addressLine, String city,
                                String state, String pincode,
                                @JsonProperty("isDefault") Boolean isDefault, String addressLine2,
                                String country, String addressType) {
    public AddAddressCommand {
        Objects.requireNonNull(fullName, AddressValidationConstants.FULL_NAME_CANNOT_BE_NULL);
        Objects.requireNonNull(phone, AddressValidationConstants.PHONE_CANNOT_BE_NULL);
        Objects.requireNonNull(addressLine, AddressValidationConstants.ADDRESS_LINE_CANNOT_BE_NULL);
        Objects.requireNonNull(city, AddressValidationConstants.CITY_CANNOT_BE_NULL);
        Objects.requireNonNull(state, AddressValidationConstants.STATE_CANNOT_BE_NULL);
        Objects.requireNonNull(pincode, AddressValidationConstants.PINCODE_CANNOT_BE_NULL);
        Objects.requireNonNull(country, AddressValidationConstants.COUNTRY_CANNOT_BE_NULL);
        Objects.requireNonNull(addressType, AddressValidationConstants.ADDRESS_TYPE_CANNOT_BE_NULL);

        if (fullName.isBlank() || fullName.length() < 2) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_FULL_NAME);
        }
        if (!phone.matches("^\\+?\\d{7,15}$")) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_PHONE);
        }
        if (addressLine.isBlank() || addressLine.length() < 5) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_ADDRESS_LINE);
        }
        if (city.isBlank() || city.length() < 2) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_CITY);
        }
        if (state.isBlank() || state.length() < 2) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_STATE);
        }
        if (!pincode.matches("^\\d{5,10}$")) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_PINCODE);
        }
        if (country.isBlank() || country.length() < 2) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_COUNTRY);
        }
        if (addressType.isBlank()) {
            throw new IllegalArgumentException(AddressValidationConstants.INVALID_ADDRESS_TYPE);
        }

    }
}