package com.adarsh.backend.feature.order.domain.model;

public enum OrderStatus {
    PENDING,

    CONFIRMED,

    PACKED,

    SHIPPED,

    OUT_FOR_DELIVERY,

    DELIVERED,

    COMPLETED,

    PARTIALLY_CANCELLED,

    CANCELLED,

    RETURN_REQUESTED,

    RETURN_APPROVED,

    RETURNED,

    REFUNDED
}
