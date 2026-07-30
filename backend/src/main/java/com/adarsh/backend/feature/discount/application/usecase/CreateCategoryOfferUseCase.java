package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.command.CreateCategoryOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateOfferResult;

public interface CreateCategoryOfferUseCase {

    CreateOfferResult execute(CreateCategoryOfferCommand command);
}
