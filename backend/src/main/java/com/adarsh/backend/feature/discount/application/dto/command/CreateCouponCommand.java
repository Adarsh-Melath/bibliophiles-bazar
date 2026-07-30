package com.adarsh.backend.feature.discount.application.dto.command;

import com.adarsh.backend.feature.discount.domain.model.DiscountType;

import java.time.LocalDateTime;

public record CreateCouponCommand(String code, DiscountType discountType, Double discountValue,
                                  Double minOrderValue, Double maxDiscountAmount,
                                  LocalDateTime expiryDate) {
}