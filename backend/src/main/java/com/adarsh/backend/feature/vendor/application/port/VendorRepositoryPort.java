package com.adarsh.backend.feature.vendor.application.port;

import java.util.List;
import java.util.Optional;

import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;

public interface VendorRepositoryPort {
    VendorApplication save(VendorApplication application);

    Optional<VendorApplication> findById(Long id);

    List<VendorApplication> findAll();

    boolean existsByEmail(String email);

    List<VendorApplication> findByStatus(ApplicationStatus status);

    Optional<VendorApplication> findByEmail(String email);
}