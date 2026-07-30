package com.adarsh.backend.feature.discount.application.dto.result;

public record ApplyCouponResult(String couponCode, Double subtotal, Double discountAmount,
                                Double finalTotal, String message) {
}
