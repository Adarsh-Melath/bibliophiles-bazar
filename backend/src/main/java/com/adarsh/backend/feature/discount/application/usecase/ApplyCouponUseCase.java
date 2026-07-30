package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.command.ApplyCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;

public interface ApplyCouponUseCase {

    ApplyCouponResult execute(String email, ApplyCouponCommand command);
}
