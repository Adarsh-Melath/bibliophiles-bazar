package com.adarsh.backend.feature.vendor.application.interactor;

import com.adarsh.backend.feature.vendor.application.interactor.constant.VendorInteractorLogConstants;
import com.adarsh.backend.feature.vendor.domain.exception.constant.VendorExceptionMessageConstants;
import com.adarsh.backend.feature.vendor.application.usecase.RejectVendorApplicationUseCase;
import com.adarsh.backend.feature.vendor.application.dto.command.RejectVendorApplicationCommand;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationCommandRepositoryPort;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationQueryRepositoryPort;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationNotFoundException;
import com.adarsh.backend.shared.application.port.EmailPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectVendorApplicationInteractor implements RejectVendorApplicationUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RejectVendorApplicationInteractor.class);

    private final VendorApplicationCommandRepositoryPort vendorApplicationCommandRepository;
    private final VendorApplicationQueryRepositoryPort vendorApplicationQueryRepositoryPort;
    private final EmailPort emailPort;

    @Override
    public void execute(Long applicationId, RejectVendorApplicationCommand command) {
        logger.info(VendorInteractorLogConstants.REJECT_VENDOR_REQUEST, applicationId);

        VendorApplication application = vendorApplicationQueryRepositoryPort.findById(applicationId).orElseThrow(() -> new ApplicationNotFoundException(VendorExceptionMessageConstants.APPLICATION_NOT_FOUND));

        application.rejectApplication(command.rejectionReason());

        vendorApplicationCommandRepository.save(application);

        logger.info(VendorInteractorLogConstants.REJECT_VENDOR_APPLICATION_REJECTED, applicationId);

        emailPort.sendVendorRejectionEmail(application.getName(), application.getEmail(), command.rejectionReason());

        logger.info(VendorInteractorLogConstants.REJECT_VENDOR_EMAIL_SENT, application.getEmail());
    }
}
