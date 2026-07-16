package com.adarsh.backend.feature.category.application.dto.command;

import com.adarsh.backend.feature.category.domain.model.CategoryType;

public record AddCategoryCommand(String description, CategoryType type) {
}
