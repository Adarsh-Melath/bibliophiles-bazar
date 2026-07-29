package com.adarsh.backend.feature.order.application.port;

import com.adarsh.backend.feature.order.domain.model.OrderItemStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.PaymentMethod;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderSearchCriteria {
    private final Long customerId;
    private final List<Long> allowedBookIds;
    private final String keyword;
    private final OrderStatus orderStatus;
    private final OrderItemStatus itemStatus;
    private final PaymentStatus paymentStatus;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Double minGrandTotal;
    private final Double maxGrandTotal;
    private final OrderSortOption sortOption;

    public OrderSearchCriteria(Builder builder) {
        this.customerId = builder.customerId;
        this.allowedBookIds = builder.allowedBookIds;
        this.keyword = builder.keyword;
        this.orderStatus = builder.orderStatus;
        this.itemStatus = builder.itemStatus;
        this.paymentStatus = builder.paymentStatus;
        this.paymentMethod = builder.paymentMethod;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.minGrandTotal = builder.minGrandTotal;
        this.maxGrandTotal = builder.maxGrandTotal;
        this.sortOption = builder.sortOption;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<Long> getAllowedBookIds() {
        return allowedBookIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderItemStatus getItemStatus() {
        return itemStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Double getMinGrandTotal() {
        return minGrandTotal;
    }

    public Double getMaxGrandTotal() {
        return maxGrandTotal;
    }

    public OrderSortOption getSortOption() {
        return sortOption;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long customerId;
        private List<Long> allowedBookIds;
        private String keyword;
        private OrderStatus orderStatus;
        private OrderItemStatus itemStatus;
        private PaymentStatus paymentStatus;
        private PaymentMethod paymentMethod;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Double minGrandTotal;
        private Double maxGrandTotal;
        private OrderSortOption sortOption;

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder allowedBookIds(List<Long> allowedBookIds) {
            this.allowedBookIds = allowedBookIds;
            return this;
        }

        public Builder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder itemStatus(OrderItemStatus itemStatus) {
            this.itemStatus = itemStatus;
            return this;
        }

        public Builder paymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
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

        public Builder minGrandTotal(Double minGrandTotal) {
            this.minGrandTotal = minGrandTotal;
            return this;
        }

        public Builder maxGrandTotal(Double maxGrandTotal) {
            this.maxGrandTotal = maxGrandTotal;
            return this;
        }

        public Builder sortOption(OrderSortOption sortOption) {
            this.sortOption = sortOption;
            return this;
        }

        public OrderSearchCriteria build() {
            return new OrderSearchCriteria(this);
        }
    }
}
