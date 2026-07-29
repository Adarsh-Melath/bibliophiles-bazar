package com.adarsh.backend.feature.returnrequest.domain.model;

import java.time.Clock;
import java.time.LocalDateTime;

public class ReturnRequest {
    private final Long id;
    private final Long orderId;
    private final Long orderItemId;
    private final Long customerId;
    private final String reason;
    private final String comments;
    private ReturnStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReturnRequest(Builder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.orderItemId = builder.orderItemId;
        this.customerId = builder.customerId;
        this.reason = builder.reason;
        this.comments = builder.comments;
        this.status = builder.status != null ? builder.status : ReturnStatus.PENDING_APPROVAL;
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now(Clock.systemDefaultZone());
        this.updatedAt = builder.updatedAt;
    }

    public Long getId() {
        return id;
    }

    public ReturnStatus getStatus() {
        return status;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    public String getComments() {
        return comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static class Builder {
        private Long id;
        private Long orderId;
        private Long orderItemId;
        private Long customerId;
        private String reason;
        private String comments;
        private ReturnStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        // ... Builder setter methods (id, orderId, orderItemId, customerId, reason, comments, status) ...
        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder status(ReturnStatus status) {
            this.status = status;
            return this;
        }

        public ReturnRequest build() {
            return new ReturnRequest(this);
        }
    }
}