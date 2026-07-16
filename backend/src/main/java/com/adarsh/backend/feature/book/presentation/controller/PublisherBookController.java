package com.adarsh.backend.feature.book.presentation.controller;

import com.adarsh.backend.feature.book.application.dto.command.AddBookCommand;
import com.adarsh.backend.feature.book.application.dto.command.UpdateBookCommand;
import com.adarsh.backend.feature.book.application.dto.result.AddBookResult;
import com.adarsh.backend.feature.book.application.dto.result.UpdateBookResult;
import com.adarsh.backend.feature.book.application.usecase.AddBookUseCase;
import com.adarsh.backend.feature.book.application.usecase.DeleteBookUseCase;
import com.adarsh.backend.feature.book.application.usecase.UpdateBookUseCase;
import com.adarsh.backend.feature.book.presentation.constant.apiconstant.PublisherBookControllerConstants;
import com.adarsh.backend.feature.book.presentation.constant.logconstant.BookControllerLogConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PublisherBookControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class PublisherBookController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherBookController.class);
    private final AddBookUseCase addBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    @PostMapping
    public ResponseEntity<AddBookResult> addBook(Authentication authentication, @RequestBody AddBookCommand command) {
        String email = authentication.getName();
        logger.info(BookControllerLogConstants.ADD_BOOK_REQUEST, command.title());
        AddBookResult result = addBookUseCase.execute(email, command);
        logger.info(BookControllerLogConstants.ADD_BOOK_SUCCESS, result.id());
        return ResponseEntity.ok(result);
    }

    @PutMapping(PublisherBookControllerConstants.BOOK_BY_SLUG_PATH)
    public ResponseEntity<UpdateBookResult> updateBook(@PathVariable String slug, @RequestBody UpdateBookCommand command) {
        logger.info(BookControllerLogConstants.UPDATE_BOOK_REQUEST, slug);
        UpdateBookResult result = updateBookUseCase.execute(slug, command);
        logger.info(BookControllerLogConstants.UPDATE_BOOK_SUCCESS, slug);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(PublisherBookControllerConstants.BOOK_BY_SLUG_PATH)
    public ResponseEntity<Void> deleteBook(@PathVariable String slug) {
        logger.info(BookControllerLogConstants.DELETE_BOOK_REQUEST, slug);
        deleteBookUseCase.execute(slug);
        logger.info(BookControllerLogConstants.DELETE_BOOK_SUCCESS, slug);
        return ResponseEntity.noContent().build();
    }
}