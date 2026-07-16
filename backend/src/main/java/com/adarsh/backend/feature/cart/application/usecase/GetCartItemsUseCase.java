package com.adarsh.backend.feature.cart.application.usecase;

import com.adarsh.backend.feature.cart.application.dto.result.GetCartItemsResult;

import java.util.List;

public interface GetCartItemsUseCase {
    List<GetCartItemsResult> execute(String email);
}
