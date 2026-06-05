package com.adarsh.backend.feature.vendor.application.interactor;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.model.AuthProvider;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.vendor.application.port.VendorRepositoryPort;
import com.adarsh.backend.feature.vendor.application.usecase.VendorApplicationApproveUseCase;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationNotFoundException;
import com.adarsh.backend.shared.application.port.EmailPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorApplicationApproveInteractor implements VendorApplicationApproveUseCase {
    private final VendorRepositoryPort vendorRepositoryPort;
    private final UserCommandRepository userCommandRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailPort emailPort;

    @Override
    public void execute(Long applicationId) {
        VendorApplication application = vendorRepositoryPort.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application Not Found"));

        application.approveApplication();

        User vendor = new User.Builder()
                .name(application.getName())
                .email(application.getEmail())
                .phone(application.getPhone())
                .enabled(true)
                .provider(AuthProvider.LOCAL)
                .role(Role.PUBLISHER)
                .build();

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        vendor.changePassword(passwordEncoder.encode(tempPassword));
        userCommandRepository.save(vendor);

        emailPort.sendVendorApprovalEmail(vendor.getName(), vendor.getEmail(), vendor.getPassword());
    }
}
