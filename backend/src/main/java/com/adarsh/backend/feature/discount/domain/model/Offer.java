package com.adarsh.backend.feature.discount.domain.model;

import java.time.Clock;
import java.time.LocalDateTime;

public class Offer {
    private final Long id;
    private final String name;
    private final Double discountPercentage;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final OfferType offerType;
    private final Long targetId;
    private final Long publisherId;
    private final boolean isActive;

    private Offer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.discountPercentage = builder.discountPercentage;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.offerType = builder.offerType;
        this.targetId = builder.targetId;
        this.publisherId = builder.publisherId;
        this.isActive = builder.isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public boolean isActive() {
        return isActive;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private Double discountPercentage;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private OfferType offerType;
        private Long targetId;
        private Long publisherId;
        private boolean isActive;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder discountPercentage(Double discountPercentage) {
            this.discountPercentage = discountPercentage;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder offerType(OfferType offerType) {
            this.offerType = offerType;
            return this;
        }

        public Builder targetId(Long targetId) {
            this.targetId = targetId;
            return this;
        }

        public Builder publisherId(Long publisherId) {
            this.publisherId = publisherId;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Offer build() {
            // Basic domain validations can also be added here before instantiation
            if (discountPercentage != null && (discountPercentage < 0 || discountPercentage > 100)) {
                throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
            }
            if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }

            return new Offer(this);
        }

    }

    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        return isActive && (startDate != null && !now.isBefore(startDate)) && (endDate != null && !now.isAfter(endDate));
    }

    public Double calculateDiscount(Double originalPrice) {
        if (!isValid() || originalPrice == null || originalPrice <= 0) {
            return 0.0;
        }
        return originalPrice * (discountPercentage / 100.0);
    }
}
