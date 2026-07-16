package com.adarsh.backend.feature.vendor.application.interactor.constant;

public final class VendorInteractorLogConstants {

    // Vendor Application Apply
    public static final String APPLY_REQUEST = "Processing vendor application for email={}";
    public static final String APPLY_SAVED = "Vendor application saved successfully for email={}";
    public static final String APPLY_EMAIL_SENT = "Confirmation email sent to vendor applicant email={}";

    // Vendor Application Approval
    public static final String APPROVE_VENDOR_REQUEST = "Processing vendor approval for application={}";
    public static final String APPROVE_VENDOR_APPLICATION_APPROVED = "Vendor application approved successfully for application={}";
    public static final String APPROVE_VENDOR_USER_CREATED = "Vendor user created successfully with id={}, email={}";
    public static final String APPROVE_VENDOR_EMAIL_SENT = "Approval notification email sent to email={}";

    // Vendor Application Rejection
    public static final String REJECT_VENDOR_REQUEST = "Processing vendor rejection for application={}";
    public static final String REJECT_VENDOR_APPLICATION_REJECTED = "Vendor application rejected successfully for application={}";
    public static final String REJECT_VENDOR_EMAIL_SENT = "Rejection notification email sent to email={}";

    // Vendor Application Query
    public static final String GET_VENDOR_APPLICATIONS_REQUEST = "Fetching vendor applications with keyword={}, status={}, page={}, size={}";
    public static final String GET_VENDOR_APPLICATIONS_FETCHED = "Vendor applications fetched successfully, count={}";

    private VendorInteractorLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
