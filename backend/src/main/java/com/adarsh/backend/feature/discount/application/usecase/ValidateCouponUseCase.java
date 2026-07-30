package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;

public interface ValidateCouponUseCase {

    ApplyCouponResult execute(String couponCode, Double subtotal);
}
