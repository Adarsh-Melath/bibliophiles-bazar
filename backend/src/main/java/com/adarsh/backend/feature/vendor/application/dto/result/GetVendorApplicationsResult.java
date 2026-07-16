package com.adarsh.backend.feature.vendor.application.dto.result;

import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;

import java.time.LocalDateTime;

public record GetVendorApplicationsResult(Long id,

                                          String name, String email, String phone,
                                          String businessRegistrationNumber, String website,
                                          String publishingSince,

                                          String businessName, String businessDescription,
                                          String category, LocalDateTime appliedAt,
                                          ApplicationStatus status, String rejectionReason,
                                          LocalDateTime reviewedAt) {


    public static GetVendorApplicationsResult fromDomain(VendorApplication application) {
        return new GetVendorApplicationsResult(application.getId(), application.getName(), application.getEmail(), application.getPhone(), application.getBusinessRegistrationNumber(), application.getWebsite(), application.getPublishingSince(), application.getBusinessName(), application.getBusinessDescription(), application.getCategory(), application.getAppliedAt(), application.getStatus(), application.getRejectionReason(), application.getReviewedAt());
    }

}