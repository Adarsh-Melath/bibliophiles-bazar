package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.result.GetOrderDetailsResult;

public interface GetOrderDetailsUseCase {
    GetOrderDetailsResult execute(String email, Long orderId);
}
