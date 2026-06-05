package com.adarsh.backend.feature.vendor.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.backend.feature.vendor.application.dto.VendorApplicationCommand;
import com.adarsh.backend.feature.vendor.application.dto.VendorApplicationRejectCommand;
import com.adarsh.backend.feature.vendor.application.usecase.VendorApplicationApplyUseCase;
import com.adarsh.backend.feature.vendor.application.usecase.VendorApplicationApproveUseCase;
import com.adarsh.backend.feature.vendor.application.usecase.VendorApplicationRejectUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vendor-application")
@RequiredArgsConstructor
public class VendorApplicationController {
    private final VendorApplicationApplyUseCase vendorApplicationApplyUseCase;
    private final VendorApplicationApproveUseCase vendorApplicationApproveUseCase;
    private final VendorApplicationRejectUseCase vendorApplicationRejectUseCase;

    @PostMapping("/apply")
    public ResponseEntity<String> apply(@RequestBody VendorApplicationCommand command) {
        vendorApplicationApplyUseCase.execute(command);
        return ResponseEntity.ok("Application submitted successfully");
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        vendorApplicationApproveUseCase.execute(id);
        return ResponseEntity.ok("Vendor approved");
    }

    @PostMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> reject(@PathVariable Long id, @RequestBody VendorApplicationRejectCommand command) {
        vendorApplicationRejectUseCase.execute(id, command);
        return ResponseEntity.ok("Application rejected");
    }
}
