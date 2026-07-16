package com.adarsh.backend.feature.vendor.domain;

import java.time.LocalDateTime;

public class VendorApplication {

    private final Long id;

    private final String name;
    private final String email;
    private final String phone;
    private final String businessRegistrationNumber;
    private final String website;
    private final String publishingSince;

    private final String businessName;
    private final String businessDescription;
    private final String category;
    private final LocalDateTime appliedAt;
    private ApplicationStatus status;
    private String rejectionReason;
    private LocalDateTime reviewedAt;

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
        this.status = builder.status != null ? builder.status : ApplicationStatus.PENDING;
        this.appliedAt = builder.appliedAt != null ? builder.appliedAt : LocalDateTime.now();
        this.rejectionReason = builder.rejectionReason;
        this.reviewedAt = builder.reviewedAt;
    }

    public static Builder builder() {
        return new Builder();
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

    public void approveApplication() {
        this.status = ApplicationStatus.APPROVED;
        this.reviewedAt = LocalDateTime.now();
    }

    public void rejectApplication(String reason) {
        this.rejectionReason = reason;
        this.status = ApplicationStatus.REJECTED;
        this.reviewedAt = LocalDateTime.now();
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
        private ApplicationStatus status;
        private LocalDateTime appliedAt;
        private String rejectionReason;
        private LocalDateTime reviewedAt;

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

        public Builder status(ApplicationStatus status) {
            this.status = status;
            return this;
        }

        public void appliedAt(LocalDateTime appliedAt) {
            this.appliedAt = appliedAt;
        }

        public void rejectionReason(String rejectionReason) {
            this.rejectionReason = rejectionReason;
        }

        public void reviewedAt(LocalDateTime reviewedAt) {
            this.reviewedAt = reviewedAt;
        }

        public VendorApplication build() {
            return new VendorApplication(this);
        }

    }
}
