package com.adarsh.backend.feature.vendor.application.dto;

import java.util.Objects;

public class VendorApplicationRejectCommand {
    private final String rejectionReason;

    public VendorApplicationRejectCommand(String rejectionReason) {
        Objects.requireNonNull(rejectionReason, "Rejection reason cannot be null");

        if (rejectionReason.isBlank() || rejectionReason.length() < 5) {
            throw new IllegalArgumentException("Rejection reason must be at least 5 characters");
        }

        this.rejectionReason = rejectionReason;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
