package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.result.SearchCustomerOrdersResult;
import com.adarsh.backend.shared.domain.pagination.PageResult;

public interface SearchCustomerOrdersUseCase {
    PageResult<SearchCustomerOrdersResult> execute(String email, String keyword, int page, int size);
}
