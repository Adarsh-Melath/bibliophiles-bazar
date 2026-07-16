package com.adarsh.backend.feature.category.infrastructure.web.exception;

import com.adarsh.backend.feature.category.domain.exception.CategoryAlreadyExistsException;
import com.adarsh.backend.feature.category.domain.exception.CategoryNotFoundException;
import com.adarsh.backend.feature.category.infrastructure.web.exception.constant.CategoryExceptionHandlerLogConstants;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CategoryExceptionController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CategoryExceptionController.class);

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex, HttpServletRequest request) {

        logger.warn(CategoryExceptionHandlerLogConstants.CATEGORY_NOT_FOUND);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex, HttpServletRequest request) {

        logger.warn(CategoryExceptionHandlerLogConstants.CATEGORY_ALREADY_EXISTS);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Already Exists", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}