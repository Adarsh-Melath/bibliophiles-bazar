package com.adarsh.backend.feature.wishlist.infrastructure.web.exception;

import com.adarsh.backend.feature.wishlist.domain.exception.*;
import com.adarsh.backend.feature.wishlist.infrastructure.web.exception.constant.WishlistExceptionHandlerLogConstants;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class WishlistExceptionHandler {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(WishlistExceptionHandler.class);
    private static final String NOT_FOUND_CODE = "NOT_FOUND";
    private static final String FORBIDDEN_CODE = "FORBIDDEN";

    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWishlistNotFoundException(WishlistNotFoundException ex, HttpServletRequest request) {
        logger.warn(WishlistExceptionHandlerLogConstants.WISHLIST_NOT_FOUND);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), NOT_FOUND_CODE, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(WishlistItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWishlistItemNotFoundException(WishlistItemNotFoundException ex, HttpServletRequest request) {
        logger.warn(WishlistExceptionHandlerLogConstants.WISHLIST_ITEM_NOT_FOUND);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), NOT_FOUND_CODE, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(WishlistAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleWishlistAccessDeniedException(WishlistAccessDeniedException ex, HttpServletRequest request) {
        logger.warn(WishlistExceptionHandlerLogConstants.WISHLIST_ACCESS_DENIED);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), FORBIDDEN_CODE, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
