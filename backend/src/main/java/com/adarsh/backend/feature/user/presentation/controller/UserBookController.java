package com.adarsh.backend.feature.user.presentation.controller;

import com.adarsh.backend.feature.book.domain.model.SortOption;
import com.adarsh.backend.feature.book.presentation.constant.apiconstant.PublicBookControllerConstants;
import com.adarsh.backend.feature.category.domain.model.CategoryType;
import com.adarsh.backend.feature.user.application.dto.result.GetPublishedBooksResult;
import com.adarsh.backend.feature.user.application.usecase.GetPublishedBooksUseCase;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserApiRoutes;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserBookRoutes;
import com.adarsh.backend.feature.user.presentation.constant.logconstant.UserControllerLogConstants;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserApiRoutes.BASE)
@RequiredArgsConstructor
public class UserBookController {

    private static final Logger logger = LoggerFactory.getLogger(UserBookController.class);
    private final GetPublishedBooksUseCase getPublishedBooksUseCase;

    @GetMapping(UserBookRoutes.BOOKS)
    public ResponseEntity<PageResult<GetPublishedBooksResult>> execute(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = PublicBookControllerConstants.DEFAULT_SORT_OPTION) SortOption sortOption,
            @RequestParam(required = false) CategoryType type,
            @RequestParam(defaultValue = PublicBookControllerConstants.DEFAULT_MIN_PRICE) Double minPrice,
            @RequestParam(defaultValue = PublicBookControllerConstants.DEFAULT_MAX_PRICE) Double maxPrice,
            @RequestParam(defaultValue = PublicBookControllerConstants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = PublicBookControllerConstants.DEFAULT_SIZE) int size
    ) {
        logger.info(UserControllerLogConstants.GET_PUBLISHED_BOOKS_REQUEST, "public-user");
        return ResponseEntity.ok(getPublishedBooksUseCase.execute(keyword, sortOption, type, minPrice, maxPrice, page, size));
    }
}
