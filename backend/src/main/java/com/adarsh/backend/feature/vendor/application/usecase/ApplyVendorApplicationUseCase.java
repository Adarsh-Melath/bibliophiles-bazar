package com.adarsh.backend.feature.vendor.application.usecase;

import com.adarsh.backend.feature.vendor.application.dto.command.ApplyVendorApplicationCommand;

public interface ApplyVendorApplicationUseCase {
    void execute(ApplyVendorApplicationCommand command);
}
