package com.adarsh.backend.feature.discount.application.port;

import com.adarsh.backend.feature.discount.domain.model.DiscountType;

public class CouponSearchCriteria {

    private final String keyword;
    private final Boolean isActive;
    private final DiscountType discountType;

    private CouponSearchCriteria(Builder builder) {
        this.keyword = builder.keyword;
        this.isActive = builder.isActive;
        this.discountType = builder.discountType;
    }

    public String getKeyword() {
        return keyword;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public static class Builder {
        private String keyword;
        private Boolean isActive;
        private DiscountType discountType;

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder discountType(DiscountType discountType) {
            this.discountType = discountType;
            return this;
        }

        public CouponSearchCriteria build() {
            return new CouponSearchCriteria(this);
        }
    }
}
