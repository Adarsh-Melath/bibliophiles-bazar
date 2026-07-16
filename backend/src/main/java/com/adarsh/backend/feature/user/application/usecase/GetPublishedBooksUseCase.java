package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.book.domain.model.SortOption;
import com.adarsh.backend.feature.user.application.dto.result.GetPublishedBooksResult;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import com.adarsh.backend.feature.category.domain.model.CategoryType;

public interface GetPublishedBooksUseCase {
    PageResult<GetPublishedBooksResult> execute(String keyword, SortOption sortOption, CategoryType type, Double minPrice, Double maxPrice, int page, int size);
}
