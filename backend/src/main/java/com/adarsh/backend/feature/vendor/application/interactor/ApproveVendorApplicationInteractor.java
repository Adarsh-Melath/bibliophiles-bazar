package com.adarsh.backend.feature.vendor.application.interactor;

import com.adarsh.backend.feature.vendor.application.interactor.constant.VendorInteractorLogConstants;
import com.adarsh.backend.feature.vendor.domain.exception.constant.VendorExceptionMessageConstants;
import com.adarsh.backend.feature.vendor.application.usecase.ApproveVendorApplicationUseCase;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.model.AuthProvider;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationCommandRepositoryPort;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationQueryRepositoryPort;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationNotFoundException;
import com.adarsh.backend.shared.application.port.EmailPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApproveVendorApplicationInteractor implements ApproveVendorApplicationUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ApproveVendorApplicationInteractor.class);

    private final VendorApplicationCommandRepositoryPort vendorApplicationCommandRepository;
    private final VendorApplicationQueryRepositoryPort vendorApplicationQueryRepositoryPort;
    private final UserCommandRepository userCommandRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailPort emailPort;

    @Override
    public void execute(Long applicationId) {
        logger.info(VendorInteractorLogConstants.APPROVE_VENDOR_REQUEST, applicationId);

        VendorApplication application = vendorApplicationQueryRepositoryPort.findById(applicationId).orElseThrow(() -> new ApplicationNotFoundException(VendorExceptionMessageConstants.APPLICATION_NOT_FOUND));

        application.approveApplication();

        logger.info(VendorInteractorLogConstants.APPROVE_VENDOR_APPLICATION_APPROVED, applicationId);

        User vendor = new User.Builder().name(application.getName()).email(application.getEmail()).phone(application.getPhone()).enabled(true).provider(AuthProvider.LOCAL).role(Role.PUBLISHER).build();

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);
        vendor.changePassword(passwordEncoder.encode(tempPassword));
        User savedVendor = userCommandRepository.save(vendor);

        logger.info(VendorInteractorLogConstants.APPROVE_VENDOR_USER_CREATED, savedVendor.getId(), savedVendor.getEmail());

        vendorApplicationCommandRepository.save(application);

        emailPort.sendVendorApprovalEmail(vendor.getName(), vendor.getEmail(), tempPassword);

        logger.info(VendorInteractorLogConstants.APPROVE_VENDOR_EMAIL_SENT, vendor.getEmail());
    }
}
