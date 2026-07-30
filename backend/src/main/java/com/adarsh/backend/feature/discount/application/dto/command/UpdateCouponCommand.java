package com.adarsh.backend.feature.discount.application.dto.command;

import com.adarsh.backend.feature.discount.domain.model.DiscountType;

import java.time.LocalDateTime;

public record UpdateCouponCommand(DiscountType discountType, Double discountValue,
                                  Double minOrderValue, Double maxDiscountAmount,
                                  LocalDateTime expiryDate, Boolean isActive) {
}
