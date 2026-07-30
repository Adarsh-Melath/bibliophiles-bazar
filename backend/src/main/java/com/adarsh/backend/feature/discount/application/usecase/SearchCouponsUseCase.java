package com.adarsh.backend.feature.discount.application.usecase;

import com.adarsh.backend.feature.discount.application.dto.result.SearchCouponsResult;
import com.adarsh.backend.feature.discount.domain.model.DiscountType;
import com.adarsh.backend.shared.domain.pagination.PageResult;

public interface SearchCouponsUseCase {

    PageResult<SearchCouponsResult> execute(String keyword, Boolean isActive, DiscountType discountType, int page, int size);
}
