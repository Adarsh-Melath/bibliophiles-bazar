package com.adarsh.backend.feature.discount.application.port;

import com.adarsh.backend.feature.discount.domain.model.OfferType;

public class OfferSearchCriteria {

    private final String keyword;
    private final OfferType offerType;
    private final Long targetId;
    private final Long publisherId;
    private final Boolean isActive;

    private OfferSearchCriteria(Builder builder) {
        this.keyword = builder.keyword;
        this.offerType = builder.offerType;
        this.targetId = builder.targetId;
        this.publisherId = builder.publisherId;
        this.isActive = builder.isActive;
    }

    public String getKeyword() {
        return keyword;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public static class Builder {
        private String keyword;
        private OfferType offerType;
        private Long targetId;
        private Long publisherId;
        private Boolean isActive;

        public Builder keyword(String keyword) {
            this.keyword = keyword;
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

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public OfferSearchCriteria build() {
            return new OfferSearchCriteria(this);
        }
    }
}
