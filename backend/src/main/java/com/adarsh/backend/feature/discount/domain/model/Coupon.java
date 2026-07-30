package com.adarsh.backend.feature.discount.domain.model;

import java.time.Clock;
import java.time.LocalDateTime;

public class Coupon {

    private final String code;
    private final DiscountType discountType;
    private final Double discountValue;
    private final Double minOrderValue;
    private final Double maxDiscountAmount;
    private final LocalDateTime expiryDate;
    private final boolean isActive;

    private Coupon(Builder builder) {
        this.code = builder.code;
        this.discountType = builder.discountType;
        this.discountValue = builder.discountValue;
        this.minOrderValue = builder.minOrderValue;
        this.maxDiscountAmount = builder.maxDiscountAmount;
        this.expiryDate = builder.expiryDate;
        this.isActive = builder.isActive;
    }

    public boolean isApplicable(Double cartSubtotal) {
        if (cartSubtotal == null || cartSubtotal < 0) return false;

        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        boolean isNotExpired = (expiryDate == null || !now.isAfter(expiryDate));
        boolean meetsMinOrder = (minOrderValue == null || cartSubtotal >= minOrderValue);

        return isActive && isNotExpired && meetsMinOrder;
    }

    public Double calculateDiscount(Double cartSubtotal) {
        if (!isApplicable(cartSubtotal)) {
            return 0.0;
        }

        double discountAmount = 0.0;

        if (discountType == DiscountType.FIXED) {
            discountAmount = discountValue;
        } else if (discountType == DiscountType.PERCENTAGE) {
            discountAmount = cartSubtotal * (discountValue / 100.0);
        }

        // Enforce maximum discount cap if it exists
        if (maxDiscountAmount != null && discountAmount > maxDiscountAmount) {
            return maxDiscountAmount;
        }

        // Ensure we don't discount more than the cart's subtotal
        return Math.min(discountAmount, cartSubtotal);
    }

    public String getCode() {
        return code;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public Double getMinOrderValue() {
        return minOrderValue;
    }

    public Double getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public boolean isActive() {
        return isActive;
    }

    // --- Builder Pattern ---
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private DiscountType discountType;
        private Double discountValue;
        private Double minOrderValue;
        private Double maxDiscountAmount;
        private LocalDateTime expiryDate;
        private boolean isActive;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder discountType(DiscountType discountType) {
            this.discountType = discountType;
            return this;
        }

        public Builder discountValue(Double discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        public Builder minOrderValue(Double minOrderValue) {
            this.minOrderValue = minOrderValue;
            return this;
        }

        public Builder maxDiscountAmount(Double maxDiscountAmount) {
            this.maxDiscountAmount = maxDiscountAmount;
            return this;
        }

        public Builder expiryDate(LocalDateTime expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Coupon build() {
            if (code == null || code.trim().isEmpty()) {
                throw new IllegalArgumentException("Coupon code cannot be null or empty");
            }
            if (discountValue != null && discountValue < 0) {
                throw new IllegalArgumentException("Discount value cannot be negative");
            }
            if (discountType == DiscountType.PERCENTAGE && discountValue != null && discountValue > 100) {
                throw new IllegalArgumentException("Percentage discount cannot exceed 100");
            }
            return new Coupon(this);
        }
    }
}