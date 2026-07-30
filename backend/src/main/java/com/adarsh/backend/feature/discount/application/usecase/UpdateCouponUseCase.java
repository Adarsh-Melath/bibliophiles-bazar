package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.command.UpdateCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateCouponResult;

public interface UpdateCouponUseCase {

    UpdateCouponResult execute(String code, UpdateCouponCommand command);
}
