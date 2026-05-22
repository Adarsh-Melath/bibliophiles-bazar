package com.adarsh.backend.feature.user.infrastructure.web.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adarsh.backend.feature.user.domain.exception.EmailNotVerifiedException;
import com.adarsh.backend.feature.user.domain.exception.InvalidCredentialException;
import com.adarsh.backend.feature.user.domain.exception.UserBlockedException;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(InvalidCredentialException.class)
        public ResponseEntity<ErrorResponse> handleInvalidCredentialException(InvalidCredentialException ex,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.UNAUTHORIZED.value(),
                                "UNAUTHORIZED",
                                ex.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        @ExceptionHandler(UserBlockedException.class)
        public ResponseEntity<ErrorResponse> handleUserBlockedException(UserBlockedException ex,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.FORBIDDEN.value(),
                                "FORBIDDEN",
                                ex.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }

        @ExceptionHandler(EmailNotVerifiedException.class)
        public ResponseEntity<ErrorResponse> handleEmailNotVerifiedException(EmailNotVerifiedException ex,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                "NOT_FOUND",
                                ex.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                "NOT_FOUND",
                                ex.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
}
