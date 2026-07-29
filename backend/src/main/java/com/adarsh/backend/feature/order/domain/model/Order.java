package com.adarsh.backend.feature.order.domain.model;

import com.adarsh.backend.feature.user.domain.model.Address;
import com.adarsh.backend.feature.order.domain.exception.OrderItemNotFoundException;
import com.adarsh.backend.feature.order.domain.exception.constant.OrderExceptionMessageConstants;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final Long id;

    private final String orderNumber;

    private final Long customerId;

    private final Address addressSnapshot;

    private final List<OrderItem> orderItemList;

    private final PaymentMethod paymentMethod;

    private final PaymentStatus paymentStatus;

    private OrderStatus orderStatus;

    private Double subtotal;

    private Double grandTotal;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Address getAddressSnapshot() {
        return addressSnapshot;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;

    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public Order(Builder builder) {
        this.id = builder.id;
        this.orderNumber = builder.orderNumber;
        this.customerId = builder.customerId;
        this.addressSnapshot = builder.addressSnapshot;
        this.orderItemList = builder.orderItemList != null ? builder.orderItemList : new java.util.ArrayList<>();
        this.paymentMethod = builder.paymentMethod;
        this.paymentStatus = builder.paymentStatus;
        this.orderStatus = builder.orderStatus;
        this.subtotal = builder.subtotal;
        this.grandTotal = builder.grandTotal;
        this.createdAt = builder.createdAt == null ? LocalDateTime.now(Clock.systemDefaultZone()) : builder.createdAt;
        this.updatedAt = builder.updatedAt == null ? null : builder.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String orderNumber;
        private Long customerId;
        private Address addressSnapshot;
        private List<OrderItem> orderItemList;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus;
        private OrderStatus orderStatus;
        private Double subtotal;
        private Double grandTotal;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder orderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder addressSnapshot(Address address) {
            this.addressSnapshot = address;
            return this;
        }

        public Builder orderItemList(List<OrderItem> orderItemList) {
            this.orderItemList = orderItemList;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder paymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder subtotal(Double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder grandTotal(Double grandTotal) {
            this.grandTotal = grandTotal;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public void addOrderItems(List<OrderItem> items) {
        this.orderItemList.addAll(items);
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCELLED;
        for (OrderItem item : orderItemList) {
            item.cancelOrderItem();
        }
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public void cancelSpecificItem(Long orderItemId) {
        for (OrderItem item : orderItemList) {
            if (item.getId().equals(orderItemId) && item.getStatus() != OrderItemStatus.CANCELLED) {
                item.cancelOrderItem();

                // Deduct the cancelled amount from the order totals
                this.subtotal -= item.getTotalPrice();
                this.grandTotal -= item.getTotalPrice();

                this.orderStatus = OrderStatus.PARTIALLY_CANCELLED;
                this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
                return;
            }
        }
        throw new OrderItemNotFoundException(OrderExceptionMessageConstants.ORDER_ITEM_NOT_FOUND);
    }

    public void returnOrderRequest() {
        this.orderStatus = OrderStatus.RETURN_REQUESTED;
        for (OrderItem item : orderItemList) {
            item.returnOrderItem();
        }
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public void returnSpecificOrderItemRequest(Long orderItemId) {
        for (OrderItem item : orderItemList) {
            if (item.getId().equals(orderItemId)) {
                item.returnOrderItem();
                break;
            }
        }
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public void changeOrderStatus(OrderStatus status) {
        this.orderStatus = status;
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }
}
