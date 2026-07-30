package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.GetCouponByCodeResult;

public interface GetCouponByCodeUseCase {

    GetCouponByCodeResult execute(String code);
}
