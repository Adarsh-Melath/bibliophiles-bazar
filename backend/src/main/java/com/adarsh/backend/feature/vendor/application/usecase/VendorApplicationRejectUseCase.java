package com.adarsh.backend.feature.vendor.application.usecase;

import com.adarsh.backend.feature.vendor.application.dto.VendorApplicationRejectCommand;

public interface VendorApplicationRejectUseCase {
    void execute(Long applicationId,VendorApplicationRejectCommand command);
}
