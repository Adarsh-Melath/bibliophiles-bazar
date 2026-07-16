package com.adarsh.backend.feature.user.domain.model;

import java.time.LocalDateTime;

public class Address {

    private final Long id;

    private final Long userId;

    private String fullName;

    private String phone;

    private String addressLine;

    private String city;

    private String state;

    private String pincode;

    private Boolean isDefault ;

    private String addressLine2;

    private String country;

    private String addressType;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Address(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.addressLine = builder.addressLine;
        this.city = builder.city;
        this.state = builder.state;
        this.pincode = builder.pincode;
        this.isDefault = builder.isDefault;
        this.addressLine2 = builder.addressLine2;
        this.country = builder.country;
        this.addressType = builder.addressType;
        this.createdAt = LocalDateTime.now();

    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
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

    public Boolean isDefault() {
        return isDefault;
    }

    public Boolean getIsDefault() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void markAsDefault() {
        this.isDefault = true;
    }

    public void markAsNonDefault() {
        this.isDefault = false;
    }

    public void updateAddress(String fullName, String phone, String addressLine, String city, String state, String pincode, String addressLine2, String country, String addressType, Boolean isDefault) {
        this.fullName = fullName;
        this.phone = phone;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.addressLine2 = addressLine2;
        this.country = country;
        this.addressType = addressType;
        this.isDefault = isDefault;
        this.updatedAt = LocalDateTime.now();
    }

    public static class Builder {

        private Long id;
        private Long userId;
        private String fullName;

        private String phone;

        private String addressLine;

        private String city;

        private String state;

        private String pincode;

        private Boolean isDefault = false;

        private String addressLine2;

        private String country;

        private String addressType;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder addressLine(String addressLine) {
            this.addressLine = addressLine;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder pincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public Builder isDefault(Boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public Builder addressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder addressType(String addressType) {
            this.addressType = addressType;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
