package com.adarsh.backend.feature.vendor.presentation.constant.logconstant;

public final class VendorControllerLogConstants {

    public static final String APPLY_REQUEST = "Received request for vendor application";
    public static final String APPLY_SUCCESS = "Vendor application submitted successfully";
    public static final String GET_ALL_REQUEST = "Received request to list vendor applications with search: {}, status: {}, page: {}, size: {}";
    public static final String GET_ALL_SUCCESS = "Vendor applications listed successfully";
    public static final String APPROVE_REQUEST = "Received request to approve vendor application ID: {}";
    public static final String APPROVE_SUCCESS = "Vendor application ID: {} approved successfully";
    public static final String REJECT_REQUEST = "Received request to reject vendor application ID: {}";
    public static final String REJECT_SUCCESS = "Vendor application ID: {} rejected successfully";

    private VendorControllerLogConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
