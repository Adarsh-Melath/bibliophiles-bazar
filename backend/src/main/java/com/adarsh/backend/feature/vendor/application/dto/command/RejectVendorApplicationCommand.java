package com.adarsh.backend.feature.vendor.application.dto.command;

import com.adarsh.backend.feature.vendor.application.dto.constant.VendorValidationConstants;

import java.util.Objects;

public record RejectVendorApplicationCommand(String rejectionReason) {
    public RejectVendorApplicationCommand {
        Objects.requireNonNull(rejectionReason, VendorValidationConstants.REJECTION_REASON_CANNOT_BE_NULL);

        if (rejectionReason.isBlank() || rejectionReason.length() < 5) {
            throw new IllegalArgumentException(VendorValidationConstants.INVALID_REJECTION_REASON);
        }
    }
}
