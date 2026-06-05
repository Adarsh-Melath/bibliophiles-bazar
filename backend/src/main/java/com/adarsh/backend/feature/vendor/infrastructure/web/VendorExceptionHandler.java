package com.adarsh.backend.feature.vendor.infrastructure.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adarsh.backend.feature.vendor.domain.exception.ApplicationAlreadyExistException;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class VendorExceptionHandler {
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ApplicationAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleApplicationAlreadyExistException(ApplicationAlreadyExistException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Already Exists",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}
