package com.adarsh.backend.feature.vendor.application.port;

import com.adarsh.backend.feature.vendor.domain.VendorApplication;

public interface VendorApplicationCommandRepositoryPort {
    VendorApplication save(VendorApplication application);
}