package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.SearchOffersResult;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.shared.domain.pagination.PageResult;

public interface SearchOffersUseCase {

    PageResult<SearchOffersResult> execute(String keyword, OfferType offerType, Long targetId, Long publisherId, Boolean isActive, int page, int size);
}
