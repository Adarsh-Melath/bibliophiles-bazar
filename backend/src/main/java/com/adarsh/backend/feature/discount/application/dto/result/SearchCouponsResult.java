package com.adarsh.backend.feature.discount.application.dto.result;

import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.feature.discount.domain.model.DiscountType;

import java.time.LocalDateTime;

public record SearchCouponsResult(String code, DiscountType discountType, Double discountValue,
                                  Double minOrderValue, Double maxDiscountAmount,
                                  LocalDateTime expiryDate, boolean isActive) {
    public static SearchCouponsResult fromDomain(Coupon coupon) {
        if (coupon == null) {
            return null;
        }
        return new SearchCouponsResult(coupon.getCode(), coupon.getDiscountType(), coupon.getDiscountValue(), coupon.getMinOrderValue(), coupon.getMaxDiscountAmount(), coupon.getExpiryDate(), coupon.isActive());
    }
}
