package com.adarsh.backend.feature.vendor.infrastructure.web.exception;

import com.adarsh.backend.feature.vendor.domain.exception.ApplicationAlreadyExistException;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationNotFoundException;
import com.adarsh.backend.feature.vendor.infrastructure.web.exception.constant.VendorExceptionHandlerLogConstants;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class VendorExceptionHandler {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendorExceptionHandler.class);

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException ex, HttpServletRequest request) {
        logger.warn(VendorExceptionHandlerLogConstants.APPLICATION_NOT_FOUND);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ApplicationAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleApplicationAlreadyExistException(ApplicationAlreadyExistException ex, HttpServletRequest request) {
        logger.warn(VendorExceptionHandlerLogConstants.APPLICATION_ALREADY_EXISTS);

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
