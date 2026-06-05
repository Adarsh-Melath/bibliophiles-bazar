package com.adarsh.backend.feature.vendor.infrastructure.web;

import java.time.LocalDateTime;

/**
 * A standardized, immutable payload sent to the frontend whenever an error
 * occurs.
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}