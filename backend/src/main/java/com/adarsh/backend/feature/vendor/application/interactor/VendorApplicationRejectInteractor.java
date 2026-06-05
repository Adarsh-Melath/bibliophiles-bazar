package com.adarsh.backend.feature.vendor.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.vendor.application.dto.VendorApplicationRejectCommand;
import com.adarsh.backend.feature.vendor.application.port.VendorRepositoryPort;
import com.adarsh.backend.feature.vendor.application.usecase.VendorApplicationRejectUseCase;
import com.adarsh.backend.feature.vendor.domain.VendorApplication;
import com.adarsh.backend.feature.vendor.domain.exception.ApplicationNotFoundException;
import com.adarsh.backend.shared.application.port.EmailPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorApplicationRejectInteractor implements VendorApplicationRejectUseCase {
    private final VendorRepositoryPort vendorRepositoryPort;
    private final EmailPort emailPort;
   
    @Override
    public void execute(Long applicationId,VendorApplicationRejectCommand command){
         VendorApplication application=vendorRepositoryPort.findById(applicationId).orElseThrow(()->new ApplicationNotFoundException("Application not found"));

         application.rejectApplication(command.getRejectionReason());

         vendorRepositoryPort.save(application);
         emailPort.sendVendorRejectionEmail(application.getName(),application.getEmail(),command.getRejectionReason());
    }
}

