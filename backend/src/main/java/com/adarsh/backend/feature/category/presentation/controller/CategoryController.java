package com.adarsh.backend.feature.category.presentation.controller;

import com.adarsh.backend.feature.category.application.dto.command.AddCategoryCommand;
import com.adarsh.backend.feature.category.application.dto.command.EditCategoryCommand;
import com.adarsh.backend.feature.category.application.dto.result.AddCategoryResult;
import com.adarsh.backend.feature.category.application.dto.result.EditCategoryResult;
import com.adarsh.backend.feature.category.application.dto.result.GetCategoriesResult;
import com.adarsh.backend.feature.category.application.usecase.AddCategoryUseCase;
import com.adarsh.backend.feature.category.application.usecase.DeleteCategoryUseCase;
import com.adarsh.backend.feature.category.application.usecase.EditCategoryUseCase;
import com.adarsh.backend.feature.category.application.usecase.GetCategoriesUseCase;
import com.adarsh.backend.feature.category.presentation.controller.constants.CategoryControllerConstants;
import com.adarsh.backend.feature.category.presentation.controller.constants.CategoryControllerLogConstants;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryControllerConstants.BASE_API_PATH)
public class CategoryController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CategoryController.class);

    private final AddCategoryUseCase addCategoryUseCase;
    private final GetCategoriesUseCase getCategoriesUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final EditCategoryUseCase editCategoryUseCase;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddCategoryResult> addCategory(@RequestBody AddCategoryCommand command) {
        logger.info(CategoryControllerLogConstants.ADD_CATEGORY_REQUEST, command.type());

        AddCategoryResult result = addCategoryUseCase.execute(command);

        logger.info(CategoryControllerLogConstants.ADD_CATEGORY_SUCCESS, result.slug());

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    //    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResult<GetCategoriesResult>> getCategories(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        logger.info(CategoryControllerLogConstants.GET_CATEGORIES_REQUEST, keyword, page, size);

        PageResult<GetCategoriesResult> result = getCategoriesUseCase.execute(keyword, page, size);

        logger.info(CategoryControllerLogConstants.GET_CATEGORIES_SUCCESS);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping(CategoryControllerConstants.CATEGORY_BY_SLUG_PATH)
    //    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable String slug) {

        logger.info(CategoryControllerLogConstants.DELETE_CATEGORY_REQUEST, slug);

        deleteCategoryUseCase.execute(slug);

        logger.info(CategoryControllerLogConstants.DELETE_CATEGORY_SUCCESS, slug);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(CategoryControllerConstants.CATEGORY_BY_SLUG_PATH)
    //    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EditCategoryResult> updateCategory(@PathVariable String slug, @RequestBody EditCategoryCommand command) {

        logger.info(CategoryControllerLogConstants.UPDATE_CATEGORY_REQUEST, slug);

        EditCategoryResult result = editCategoryUseCase.execute(slug, command);

        logger.info(CategoryControllerLogConstants.UPDATE_CATEGORY_SUCCESS, slug);

        return ResponseEntity.ok(result);
    }
}