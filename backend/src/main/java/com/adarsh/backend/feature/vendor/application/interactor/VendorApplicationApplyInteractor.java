package com.adarsh.backend.feature.vendor.application.interactor;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserAlreadyExistException;
import com.adarsh.backend.feature.vendor.application.dto.command.ApplyVendorApplicationCommand;
import com.adarsh.backend.feature.vendor.application.interactor.constant.VendorExceptionMessageConstants;
import com.adarsh.backend.feature.vendor.application.interactor.constant.VendorInteractorLogConstants;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationCommandRepositoryPort;
import com.adarsh.backend.feature.vendor.application.port.VendorApplicationQueryRepositoryPort;
import com.adarsh.backend.feature.vendor.application.usecase.ApplyVendorApplicationUseCase;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationAlreadyExistException;
import com.adarsh.backend.feature.auth.domain.exception.constants.AuthExceptionMessageConstants;
import com.adarsh.backend.shared.application.port.EmailPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorApplicationApplyInteractor implements ApplyVendorApplicationUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(VendorApplicationApplyInteractor.class);

    private final UserCommandRepository userCommandRepository;
    private final VendorApplicationCommandRepositoryPort vendorApplicationCommandRepository;
    private final VendorApplicationQueryRepositoryPort vendorApplicationQueryRepositoryPort;
    private final EmailPort emailPort;

    @Override
    public void execute(ApplyVendorApplicationCommand command) {
        logger.info(VendorInteractorLogConstants.APPLY_REQUEST, command.email());

        if (vendorApplicationQueryRepositoryPort.findByEmail(command.email()).isPresent()) {
            throw new ApplicationAlreadyExistException(VendorExceptionMessageConstants.APPLICATION_ALREADY_EXISTS);
        }

        if (userCommandRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistException(AuthExceptionMessageConstants.EMAIL_ALREADY_EXISTS);
        }

        VendorApplication application = new VendorApplication.Builder().name(command.name()).email(command.email()).phone(command.phone()).businessName(command.businessName()).businessDescription(command.businessDescription()).category(command.category()).businessRegistrationNumber(command.businessRegistrationNumber()).website(command.website()).publishingSince(command.publishingSince()).build();

        vendorApplicationCommandRepository.save(application);

        logger.info(VendorInteractorLogConstants.APPLY_SAVED, command.email());

        emailPort.sendVendorApplicationConfirmation(command.name(), command.email());

        logger.info(VendorInteractorLogConstants.APPLY_EMAIL_SENT, command.email());
    }
}