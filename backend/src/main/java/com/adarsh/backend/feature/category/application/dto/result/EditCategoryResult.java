package com.adarsh.backend.feature.category.application.dto.result;

import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.category.domain.model.CategoryType;

import java.time.LocalDateTime;

public record EditCategoryResult(Long id, CategoryType type, String description, String slug,
                                 LocalDateTime createdAt) {
    public static EditCategoryResult fromDomain(Category category) {
        return new EditCategoryResult(category.getId(), category.getType(), category.getDescription(), category.getSlug(), category.getCreatedAt());
    }
}
