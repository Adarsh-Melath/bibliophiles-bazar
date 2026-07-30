package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.command.CreateProductOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateOfferResult;

public interface CreateProductOfferUseCase {

    CreateOfferResult execute(String email, CreateProductOfferCommand command);
}
