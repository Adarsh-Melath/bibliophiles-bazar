package com.adarsh.backend.feature.category.application.interactor;

import com.adarsh.backend.feature.category.application.dto.command.EditCategoryCommand;
import com.adarsh.backend.feature.category.application.dto.result.EditCategoryResult;
import com.adarsh.backend.feature.category.application.interactor.constant.CategoryInteractorLogConstants;
import com.adarsh.backend.feature.category.application.port.CategoryCommandPort;
import com.adarsh.backend.feature.category.application.port.CategoryQueryPort;
import com.adarsh.backend.feature.category.application.usecase.EditCategoryUseCase;
import com.adarsh.backend.feature.category.domain.exception.CategoryNotFoundException;
import com.adarsh.backend.feature.category.domain.exception.constant.CategoryExceptionConstants;
import com.adarsh.backend.feature.category.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditCategoryInteractor implements EditCategoryUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EditCategoryInteractor.class);
    private final CategoryCommandPort categoryCommandPort;
    private final CategoryQueryPort categoryQueryPort;

    @Override
    public EditCategoryResult execute(String slug, EditCategoryCommand command) {
        logger.info(CategoryInteractorLogConstants.EDIT_CATEGORY_REQUEST, slug);
        Category category = categoryQueryPort.findBySlug(slug).orElseThrow(() -> new CategoryNotFoundException(CategoryExceptionConstants.CATEGORY_NOT_FOUND));

        category.update(command.description());
        categoryCommandPort.save(category);
        logger.info(CategoryInteractorLogConstants.EDIT_CATEGORY_SAVED, category.getId());
        return EditCategoryResult.fromDomain(category);
    }
}
