package com.adarsh.backend.feature.vendor.presentation.controller;

import com.adarsh.backend.feature.vendor.application.dto.command.ApplyVendorApplicationCommand;
import com.adarsh.backend.feature.vendor.application.usecase.ApplyVendorApplicationUseCase;
import com.adarsh.backend.feature.vendor.presentation.constant.apiconstant.VendorControllerConstants;
import com.adarsh.backend.feature.vendor.presentation.constant.logconstant.VendorControllerLogConstants;
import com.adarsh.backend.feature.vendor.presentation.constant.successmessageconstant.VendorSuccessMessageConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VendorControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class VendorApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(VendorApplicationController.class);
    private final ApplyVendorApplicationUseCase vendorApplicationApplyUseCase;

    @PostMapping(VendorControllerConstants.APPLY_PATH)
    public ResponseEntity<String> apply(@RequestBody ApplyVendorApplicationCommand command) {
        logger.info(VendorControllerLogConstants.APPLY_REQUEST);
        vendorApplicationApplyUseCase.execute(command);
        logger.info(VendorControllerLogConstants.APPLY_SUCCESS);
        return ResponseEntity.ok(VendorSuccessMessageConstants.APPLICATION_SUBMITTED);
    }
}
