package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.GetActiveCouponsResult;

import java.util.List;

public interface GetActiveCouponsUseCase {

    List<GetActiveCouponsResult> execute();
}
