package com.adarsh.backend.feature.category.application.usecase;

import com.adarsh.backend.feature.category.application.dto.result.GetCategoriesResult;
import com.adarsh.backend.shared.domain.pagination.PageResult;

public interface GetCategoriesUseCase {
    PageResult<GetCategoriesResult> execute(String keyword, int page, int size);
}
