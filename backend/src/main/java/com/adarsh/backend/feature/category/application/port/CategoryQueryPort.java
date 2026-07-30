package com.adarsh.backend.feature.category.application.port;

import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.category.domain.model.CategoryType;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.Optional;

public interface CategoryQueryPort {

    Optional<Category> findById(Long id);

    Optional<Category> findByType(CategoryType type);

    Optional<Category> findBySlug(String slug);

    PageResult<Category> search(CategorySearchCriteria criteria, PageQuery query);
}
