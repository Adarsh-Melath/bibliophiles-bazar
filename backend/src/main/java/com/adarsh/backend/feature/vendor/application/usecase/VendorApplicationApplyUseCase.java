package com.adarsh.backend.feature.vendor.application.usecase;

import com.adarsh.backend.feature.vendor.application.dto.VendorApplicationCommand;

public interface VendorApplicationApplyUseCase {
    void execute(VendorApplicationCommand command);
}
