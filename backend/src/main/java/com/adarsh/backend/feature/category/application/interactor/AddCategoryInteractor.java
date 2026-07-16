package com.adarsh.backend.feature.category.application.interactor;

import com.adarsh.backend.feature.category.application.dto.command.AddCategoryCommand;
import com.adarsh.backend.feature.category.application.dto.result.AddCategoryResult;
import com.adarsh.backend.feature.category.application.interactor.constant.CategoryInteractorLogConstants;
import com.adarsh.backend.feature.category.application.port.CategoryCommandPort;
import com.adarsh.backend.feature.category.application.usecase.AddCategoryUseCase;
import com.adarsh.backend.feature.category.domain.SlugGenerator;
import com.adarsh.backend.feature.category.domain.exception.CategoryAlreadyExistsException;
import com.adarsh.backend.feature.category.domain.exception.constant.CategoryExceptionConstants;
import com.adarsh.backend.feature.category.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AddCategoryInteractor implements AddCategoryUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddCategoryInteractor.class);
    private final CategoryCommandPort categoryCommandPort;

    @Override
    public AddCategoryResult execute(AddCategoryCommand command) {
        logger.info(CategoryInteractorLogConstants.ADD_CATEGORY_REQUEST, command.type());
        if (categoryCommandPort.existsByCategoryType(command.type())) {
            throw new CategoryAlreadyExistsException(CategoryExceptionConstants.CATEGORY_ALREADY_EXISTS);
        }

        String slug = SlugGenerator.slugGenerator(command.type().name());

        Category newCategory = new Category.Builder().type(command.type()).slug(slug).description(command.description()).build();

        Category savedCategory = categoryCommandPort.save(newCategory);
        logger.info(CategoryInteractorLogConstants.ADD_CATEGORY_SAVED, savedCategory.getId());

        return AddCategoryResult.fromDomain(savedCategory);
    }
}
