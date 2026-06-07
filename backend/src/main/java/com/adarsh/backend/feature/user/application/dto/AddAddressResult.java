package com.adarsh.backend.feature.user.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddAddressResult {

    private final Long id;
    private final String fullName;
    private final String phone;
    private final String addressLine;
    private final String city;
    private final String state;
    private final String pincode;
    @JsonProperty("isDefault")
    private final boolean isDefault;
    private final String addressLine2;
    private final String country;
    private final String addressType;

    public AddAddressResult(Long id, String fullName, String phone, String addressLine, String city, String state, String pincode,
            boolean isDefault, String addressLine2, String country, String addressType) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.isDefault = isDefault;
        this.addressLine2 = addressLine2;
        this.country = country;
        this.addressType = addressType;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPincode() {
        return pincode;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCountry() {
        return country;
    }

    public String getAddressType() {
        return addressType;
    }
}
