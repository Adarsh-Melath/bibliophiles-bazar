package com.adarsh.backend.feature.vendor.domain;

import java.time.LocalDateTime;

public class VendorApplication {

    private Long id;

    private final String name;
    private final String email;
    private final String phone;
    private final String businessRegistrationNumber;
    private final String website;
    private final String publishingSince;

    private final String businessName;
    private final String businessDescription;
    private final String category;

    private ApplicationStatus status;
    private String rejectionReason;

    private LocalDateTime appliedAt;

    private LocalDateTime reviewedAt;

    public static Builder builder() {
        return new Builder();
    }

    public VendorApplication(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.businessRegistrationNumber = builder.businessRegistrationNumber;
        this.website = builder.website;
        this.publishingSince = builder.publishingSince;
        this.businessName = builder.businessName;
        this.businessDescription = builder.businessDescription;
        this.category = builder.category;
        this.status = ApplicationStatus.PENDING;
        this.appliedAt = LocalDateTime.now();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String businessRegistrationNumber;
        private String website;
        private String publishingSince;
        private String businessName;
        private String businessDescription;
        private String category;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder businessRegistrationNumber(String businessRegistrationNumber) {
            this.businessRegistrationNumber = businessRegistrationNumber;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder publishingSince(String publishingSince) {
            this.publishingSince = publishingSince;
            return this;
        }

        public Builder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public Builder businessDescription(String businessDescription) {
            this.businessDescription = businessDescription;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public VendorApplication build() {
            return new VendorApplication(this);
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getPublishingSince() {
        return publishingSince;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public String getCategory() {
        return category;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }

}
