package com.adarsh.backend.feature.category.application.usecase;

import com.adarsh.backend.feature.category.application.dto.command.AddCategoryCommand;
import com.adarsh.backend.feature.category.application.dto.result.AddCategoryResult;

public interface AddCategoryUseCase {
    AddCategoryResult execute(AddCategoryCommand command);
}
