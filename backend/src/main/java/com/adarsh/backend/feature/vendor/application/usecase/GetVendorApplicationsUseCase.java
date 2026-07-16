package com.adarsh.backend.feature.vendor.application.usecase;

import com.adarsh.backend.feature.vendor.application.dto.result.GetVendorApplicationsResult;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;

public interface GetVendorApplicationsUseCase {
    PageResult<GetVendorApplicationsResult> execute(String keyword, ApplicationStatus status, int page, int size);
}
