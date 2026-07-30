package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;

public interface RemoveCouponUseCase {

    ApplyCouponResult execute(String email);
}
