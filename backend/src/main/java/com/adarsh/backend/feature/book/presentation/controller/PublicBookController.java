package com.adarsh.backend.feature.book.presentation.controller;

import com.adarsh.backend.feature.book.application.dto.result.GetBooksResult;
import com.adarsh.backend.feature.book.application.usecase.GetBooksUseCase;
import com.adarsh.backend.feature.book.presentation.constant.apiconstant.PublicBookControllerConstants;
import com.adarsh.backend.feature.book.presentation.constant.logconstant.BookControllerLogConstants;
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
@RequestMapping(PublicBookControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class PublicBookController {

    private static final Logger logger = LoggerFactory.getLogger(PublicBookController.class);
    private final GetBooksUseCase getBooksUseCase;

    @GetMapping
    public ResponseEntity<PageResult<GetBooksResult>> getBooks(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        logger.info(BookControllerLogConstants.GET_BOOKS_REQUEST, keyword, page, size);
        PageResult<GetBooksResult> result = getBooksUseCase.execute(keyword, page, size);
        logger.info(BookControllerLogConstants.GET_BOOKS_SUCCESS);

        return ResponseEntity.ok(result);
    }
}