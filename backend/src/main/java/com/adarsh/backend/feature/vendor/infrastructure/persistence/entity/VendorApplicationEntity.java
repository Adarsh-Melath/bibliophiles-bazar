package com.adarsh.backend.feature.vendor.infrastructure.persistence.entity;

import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "vendor_applications")
public class VendorApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String businessRegistrationNumber;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String publishingSince;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    @Size(min = 20, max = 200)
    private String businessDescription;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    private String rejectionReason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt = LocalDateTime.now();

    private LocalDateTime reviewedAt;
}