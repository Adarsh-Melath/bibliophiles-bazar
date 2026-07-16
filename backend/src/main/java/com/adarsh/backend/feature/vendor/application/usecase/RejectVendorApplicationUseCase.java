package com.adarsh.backend.feature.vendor.application.usecase;

import com.adarsh.backend.feature.vendor.application.dto.command.RejectVendorApplicationCommand;

public interface RejectVendorApplicationUseCase {
    void execute(Long applicationId, RejectVendorApplicationCommand command);
}
