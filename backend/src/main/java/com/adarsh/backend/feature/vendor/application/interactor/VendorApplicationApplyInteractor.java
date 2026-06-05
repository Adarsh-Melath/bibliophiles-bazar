package com.adarsh.backend.feature.vendor.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.exception.UserAlreadyExistException;
import com.adarsh.backend.feature.vendor.application.dto.VendorApplicationCommand;
import com.adarsh.backend.feature.vendor.application.port.VendorRepositoryPort;
import com.adarsh.backend.feature.vendor.application.usecase.VendorApplicationApplyUseCase;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationAlreadyExistException;
import com.adarsh.backend.shared.application.port.EmailPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorApplicationInteractor implements VendorApplicationApplyUseCase {
    private final UserCommandRepository userCommandRepository;
    private final VendorRepositoryPort vendorRepositoryPort;
    private final EmailPort emailPort;
    
    @Override
    public void execute(VendorApplicationCommand command){
        if(vendorRepositoryPort.existsByEmail(command.getEmail())){
            throw new ApplicationAlreadyExistException("Application already exist");
        }

        if(userCommandRepository.existsByEmail(command.getEmail())){
            throw new UserAlreadyExistException("User with this email already exists");
        }

        VendorApplication application=new VendorApplication.Builder()
            .name(command.getName())
            .email(command.getEmail())
            .phone(command.getPhone())
            .businessName(command.getBusinessName())
            .businessDescription(command.getBusinessDescription())
            .category(command.getCategory())
            .businessRegistrationNumber(command.getBusinessRegistrationNumber())
            .website(command.getWebsite())
            .publishingSince(command.getPublishingSince()).build();

            vendorRepositoryPort.save(application);

            emailPort.sendVendorApplicationConfirmation(command.getName(),command.getEmail());
    }
}