package com.adarsh.backend.feature.vendor.application.port;

import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;

public record VendorApplicationSearchCriteria(String keyword, ApplicationStatus status) {
}
