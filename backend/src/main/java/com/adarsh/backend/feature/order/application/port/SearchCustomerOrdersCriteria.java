package com.adarsh.backend.feature.order.application.port;

public record SearchCustomerOrdersCriteria(Long customerId, String keyword) {
}
