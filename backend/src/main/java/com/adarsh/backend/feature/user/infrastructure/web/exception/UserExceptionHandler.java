package com.adarsh.backend.feature.user.infrastructure.web.exception;

import com.adarsh.backend.feature.user.domain.exception.*;
import com.adarsh.backend.feature.user.infrastructure.web.exception.constant.UserExceptionHandlerLogConstants;
import com.adarsh.backend.shared.domain.exception.MediaUploadException;
import com.adarsh.backend.shared.infrastructure.web.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFoundException(AddressNotFoundException ex, HttpServletRequest request) {
        logger.warn(UserExceptionHandlerLogConstants.ADDRESS_NOT_FOUND, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MediaUploadException.class)
    public ResponseEntity<ErrorResponse> handleMediaUploadException(MediaUploadException ex, HttpServletRequest request) {
        logger.error(UserExceptionHandlerLogConstants.MEDIA_UPLOAD_FAILED, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Media Upload Failed", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(CannotBlockAdminUserException.class)
    public ResponseEntity<ErrorResponse> handleCannotBlockAdminUserException(CannotBlockAdminUserException ex, HttpServletRequest request) {
        logger.warn(UserExceptionHandlerLogConstants.CANNOT_BLOCK_ADMIN, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), "FORBIDDEN", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialException(InvalidCredentialException ex, HttpServletRequest request) {
        logger.warn(UserExceptionHandlerLogConstants.INVALID_CREDENTIAL, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(UserBlockedException.class)
    public ResponseEntity<ErrorResponse> handleUserBlockedException(UserBlockedException ex, HttpServletRequest request) {
        logger.warn(UserExceptionHandlerLogConstants.USER_BLOCKED, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), "FORBIDDEN", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotVerifiedException(EmailNotVerifiedException ex, HttpServletRequest request) {
        logger.warn(UserExceptionHandlerLogConstants.EMAIL_NOT_VERIFIED, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        logger.warn(UserExceptionHandlerLogConstants.USER_NOT_FOUND, ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "NOT_FOUND", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}