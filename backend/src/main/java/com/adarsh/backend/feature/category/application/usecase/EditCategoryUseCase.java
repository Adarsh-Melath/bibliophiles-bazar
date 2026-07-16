package com.adarsh.backend.feature.category.application.usecase;

import com.adarsh.backend.feature.category.application.dto.command.EditCategoryCommand;
import com.adarsh.backend.feature.category.application.dto.result.EditCategoryResult;

public interface EditCategoryUseCase {
    EditCategoryResult execute(String slug, EditCategoryCommand command);
}
