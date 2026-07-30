package com.adarsh.backend.feature.discount.application.dto.result;

import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.feature.discount.domain.model.DiscountType;

import java.time.LocalDateTime;

public record GetCouponByCodeResult(String code, DiscountType discountType, Double discountValue,
                                    Double minOrderValue, Double maxDiscountAmount,
                                    LocalDateTime expiryDate, boolean isActive) {
    public static GetCouponByCodeResult fromDomain(Coupon coupon) {
        if (coupon == null) {
            return null;
        }
        return new GetCouponByCodeResult(coupon.getCode(), coupon.getDiscountType(), coupon.getDiscountValue(), coupon.getMinOrderValue(), coupon.getMaxDiscountAmount(), coupon.getExpiryDate(), coupon.isActive());
    }
}
