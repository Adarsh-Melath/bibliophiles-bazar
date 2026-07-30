package com.adarsh.backend.feature.discount.infrastructure.web.exception;

import com.adarsh.backend.feature.discount.domain.exception.CouponAlreadyExistsException;
import com.adarsh.backend.feature.discount.domain.exception.CouponExpiredException;
import com.adarsh.backend.feature.discount.domain.exception.CouponNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.InvalidCouponException;
import com.adarsh.backend.feature.discount.domain.exception.InvalidOfferException;
import com.adarsh.backend.feature.discount.domain.exception.OfferNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.UnauthorizedOfferAccessException;
import com.adarsh.backend.feature.discount.infrastructure.web.exception.constant.DiscountExceptionHandlerLogConstants;
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
public class DiscountExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DiscountExceptionHandler.class);
    private static final String NOT_FOUND = "Not Found";
    private static final String BAD_REQUEST = "Bad Request";
    private static final String CONFLICT = "Conflict";
    private static final String FORBIDDEN = "Forbidden";

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCouponNotFound(CouponNotFoundException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.COUPON_NOT_FOUND);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.NOT_FOUND.value(), NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CouponAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCouponAlreadyExists(CouponAlreadyExistsException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.COUPON_ALREADY_EXISTS);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.CONFLICT.value(), CONFLICT, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidCouponException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCoupon(InvalidCouponException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.INVALID_COUPON);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.BAD_REQUEST.value(), BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(CouponExpiredException.class)
    public ResponseEntity<ErrorResponse> handleCouponExpired(CouponExpiredException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.COUPON_EXPIRED);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.BAD_REQUEST.value(), BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOfferNotFound(OfferNotFoundException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.OFFER_NOT_FOUND);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.NOT_FOUND.value(), NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidOfferException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOffer(InvalidOfferException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.INVALID_OFFER);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.BAD_REQUEST.value(), BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnauthorizedOfferAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedOfferAccess(UnauthorizedOfferAccessException ex, HttpServletRequest request) {
        logger.warn(DiscountExceptionHandlerLogConstants.UNAUTHORIZED_OFFER_ACCESS);
        ErrorResponse response = new ErrorResponse(LocalDateTime.now(Clock.systemDefaultZone()), HttpStatus.FORBIDDEN.value(), FORBIDDEN, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
