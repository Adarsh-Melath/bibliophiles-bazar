package com.adarsh.backend.feature.user.application.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetAddressResult(Long id, String fullName, String phone, String addressLine,
                               String city, String state, String pincode,
                               @JsonProperty("isDefault") boolean isDefault, String addressLine2,
                               String country, String addressType) {
}
