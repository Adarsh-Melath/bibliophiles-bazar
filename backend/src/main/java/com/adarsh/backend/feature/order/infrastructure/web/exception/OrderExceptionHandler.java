package com.adarsh.backend.feature.order.infrastructure.web.exception;

import com.adarsh.backend.feature.order.domain.exception.OrderNotFoundException;
import com.adarsh.backend.feature.order.domain.exception.OrderItemNotFoundException;
import com.adarsh.backend.feature.order.domain.exception.InvalidOrderStateException;
import com.adarsh.backend.feature.order.domain.exception.InvoiceGenerationException;
import com.adarsh.backend.feature.order.infrastructure.web.exception.constant.OrderExceptionHandlerLogConstants;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.LocalDateTime;

@RestControllerAdvice
public class OrderExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderExceptionHandler.class);
    private static final String NOT_FOUND = "Not Found";
    private static final String BAD_REQUEST = "Bad Request";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex, HttpServletRequest request) {
        logger.warn(OrderExceptionHandlerLogConstants.ORDER_NOT_FOUND);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.NOT_FOUND.value(), NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderItemNotFoundException(OrderItemNotFoundException ex, HttpServletRequest request) {
        logger.warn(OrderExceptionHandlerLogConstants.ORDER_ITEM_NOT_FOUND);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.NOT_FOUND.value(), NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidOrderStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOrderStateException(InvalidOrderStateException ex, HttpServletRequest request) {
        logger.warn(OrderExceptionHandlerLogConstants.INVALID_ORDER_STATE);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.BAD_REQUEST.value(), BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvoiceGenerationException.class)
    public ResponseEntity<ErrorResponse> handleInvoiceGenerationException(InvoiceGenerationException ex, HttpServletRequest request) {
        logger.error(OrderExceptionHandlerLogConstants.FAILED_TO_GENERATE_INVOICE, ex);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
