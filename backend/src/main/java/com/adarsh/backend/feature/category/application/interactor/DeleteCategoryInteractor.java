package com.adarsh.backend.feature.category.application.interactor;

import com.adarsh.backend.feature.category.application.interactor.constant.CategoryInteractorLogConstants;
import com.adarsh.backend.feature.category.application.port.CategoryCommandPort;
import com.adarsh.backend.feature.category.application.port.CategoryQueryPort;
import com.adarsh.backend.feature.category.application.usecase.DeleteCategoryUseCase;
import com.adarsh.backend.feature.category.domain.exception.CategoryNotFoundException;
import com.adarsh.backend.feature.category.domain.exception.constant.CategoryExceptionConstants;
import com.adarsh.backend.feature.category.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryInteractor implements DeleteCategoryUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteCategoryInteractor.class);
    private final CategoryCommandPort categoryCommandPort;
    private final CategoryQueryPort categoryQueryPort;

    @Override
    public void execute(String slug) {
        logger.info(CategoryInteractorLogConstants.DELETE_CATEGORY_REQUEST, slug);
        Category category = categoryQueryPort.findBySlug(slug).orElseThrow(() -> new CategoryNotFoundException(CategoryExceptionConstants.CATEGORY_NOT_FOUND));

        category.softDelete();
        categoryCommandPort.save(category);
        logger.info(CategoryInteractorLogConstants.DELETE_CATEGORY_SAVED, category.getId());
    }
}
