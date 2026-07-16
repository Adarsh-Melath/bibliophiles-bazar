package com.adarsh.backend.feature.book.infrastructure.web.exception;

import com.adarsh.backend.feature.book.domain.exception.BookAlreadyExistsException;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.infrastructure.web.exception.constant.BookExceptionHandlerLogConstants;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class BookExceptionHandler {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BookExceptionHandler.class);

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBookAlreadyExistsException(BookAlreadyExistsException ex, HttpServletRequest request) {
        logger.warn(BookExceptionHandlerLogConstants.BOOK_ALREADY_EXISTS);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex, HttpServletRequest request) {
        logger.warn(BookExceptionHandlerLogConstants.BOOK_NOT_FOUND);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not " + "Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
