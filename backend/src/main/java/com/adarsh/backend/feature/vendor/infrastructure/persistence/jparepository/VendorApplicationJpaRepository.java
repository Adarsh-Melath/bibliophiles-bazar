package com.adarsh.backend.feature.vendor.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.infrastructure.persistence.entity.VendorApplicationEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VendorApplicationJpaRepository extends JpaRepository<VendorApplicationEntity, Long> {
    VendorApplicationEntity save(VendorApplication application);

    @NullMarked
    Optional<VendorApplicationEntity> findById(Long id);

    Optional<VendorApplicationEntity> findByEmail(String email);

    @Query("SELECT a FROM VendorApplicationEntity a WHERE " + "(:keyword IS " + "NULL OR LOWER(a.businessName) LIKE LOWER(CONCAT('%',:keyword, " + "'%')) OR LOWER(a.email) LIKE LOWER(CONCAT('%',:keyword, '%')))" + "AND (:status IS NULL or a.status = :status)")
    Page<VendorApplicationEntity> searchVendorApplication(@Param("keyword") String keyword, @Param("status") ApplicationStatus status, Pageable pageable);
}

