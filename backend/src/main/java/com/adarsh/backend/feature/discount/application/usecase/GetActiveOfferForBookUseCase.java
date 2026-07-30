package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.GetActiveOfferForBookResult;

public interface GetActiveOfferForBookUseCase {

    GetActiveOfferForBookResult execute(Long bookId);
}
