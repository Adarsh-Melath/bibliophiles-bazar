package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.command.CreateCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateCouponResult;

public interface CreateCouponUseCase {
    CreateCouponResult execute(CreateCouponCommand command);
}