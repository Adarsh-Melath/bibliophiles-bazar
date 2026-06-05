package com.adarsh.backend.feature.vendor.infrastructure.persistence.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.entity.VendorApplicationEntity;

public interface VendorApplicationJpaRepository extends JpaRepository<VendorApplicationEntity, Long> {
    VendorApplicationEntity save(VendorApplication application);

    Optional<VendorApplicationEntity> findById(Long id);

    List<VendorApplicationEntity> findAll();

    boolean existsByEmail(String email);

    List<VendorApplicationEntity> findByStatus(ApplicationStatus status);

    Optional<VendorApplicationEntity> findByEmail(String email);
}