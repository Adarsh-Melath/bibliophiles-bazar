package com.adarsh.backend.feature.cart.infrastructure.web.exception;

import com.adarsh.backend.feature.cart.domain.exception.CartItemNotFoundException;
import com.adarsh.backend.feature.cart.domain.exception.InsufficientStockException;
import com.adarsh.backend.feature.cart.domain.exception.CartItemQuantityLimitExceededException;
import com.adarsh.backend.feature.cart.infrastructure.web.exception.constant.CartExceptionHandlerLogConstants;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CartExceptionHandler {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CartExceptionHandler.class);

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartItemNotFoundException(CartItemNotFoundException ex, HttpServletRequest request) {
        logger.warn(CartExceptionHandlerLogConstants.CART_ITEM_NOT_FOUND);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException ex, HttpServletRequest request) {
        logger.warn(CartExceptionHandlerLogConstants.INSUFFICIENT_STOCK);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CartItemQuantityLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleCartItemQuantityLimitExceededException(CartItemQuantityLimitExceededException ex, HttpServletRequest request) {
        logger.warn(CartExceptionHandlerLogConstants.QUANTITY_LIMIT_EXCEEDED);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
