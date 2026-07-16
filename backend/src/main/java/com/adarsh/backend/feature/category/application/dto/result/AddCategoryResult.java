package com.adarsh.backend.feature.category.application.dto.result;

import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.category.domain.model.CategoryType;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public record AddCategoryResult(Long id, CategoryType type, String description, String slug,
                                LocalDateTime createdAt) {
    public static AddCategoryResult fromDomain(Category category) {
        log.info("category id: {}", category.getId());
        return new AddCategoryResult(category.getId(), category.getType(), category.getDescription(), category.getSlug(), category.getCreatedAt());
    }
}
