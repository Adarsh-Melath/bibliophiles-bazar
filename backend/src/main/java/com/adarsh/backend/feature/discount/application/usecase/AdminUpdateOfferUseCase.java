package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.command.UpdateOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateOfferResult;

public interface AdminUpdateOfferUseCase {
    UpdateOfferResult execute(Long offerId, UpdateOfferCommand command);
}
