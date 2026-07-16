package com.adarsh.backend.feature.auth.infrastructure.web.exception;

import com.adarsh.backend.feature.auth.domain.exception.InvalidOtpException;
import com.adarsh.backend.feature.auth.domain.exception.RefreshTokenExpiredException;
import com.adarsh.backend.feature.auth.domain.exception.RefreshTokenNotFoundException;
import com.adarsh.backend.feature.auth.infrastructure.web.exception.constant.AuthExceptionHandlerLogConstants;
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
public class AuthExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthExceptionHandler.class);

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOtpException(InvalidOtpException ex, HttpServletRequest request) {
        logger.warn(AuthExceptionHandlerLogConstants.INVALID_OTP);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), request.getRequestURI());
        logger.debug(AuthExceptionHandlerLogConstants.ERROR_RESPONSE_SENT, HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenExpiredException(RefreshTokenExpiredException ex, HttpServletRequest request) {
        logger.warn(AuthExceptionHandlerLogConstants.REFRESH_TOKEN_EXPIRED);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.GONE.value(), " Gone", ex.getMessage(), request.getRequestURI());
        logger.debug(AuthExceptionHandlerLogConstants.ERROR_RESPONSE_SENT, HttpStatus.GONE.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.GONE).body(errorResponse);
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenNotFoundException(RefreshTokenNotFoundException ex, HttpServletRequest request) {
        logger.warn(AuthExceptionHandlerLogConstants.REFRESH_TOKEN_NOT_FOUND);
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());
        logger.debug(AuthExceptionHandlerLogConstants.ERROR_RESPONSE_SENT, HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
