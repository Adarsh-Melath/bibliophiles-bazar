package com.adarsh.backend.feature.vendor.presentation.controller;

import com.adarsh.backend.feature.vendor.application.dto.command.RejectVendorApplicationCommand;
import com.adarsh.backend.feature.vendor.application.dto.result.GetVendorApplicationsResult;
import com.adarsh.backend.feature.vendor.application.usecase.ApproveVendorApplicationUseCase;
import com.adarsh.backend.feature.vendor.application.usecase.GetVendorApplicationsUseCase;
import com.adarsh.backend.feature.vendor.application.usecase.RejectVendorApplicationUseCase;
import com.adarsh.backend.feature.vendor.domain.ApplicationStatus;
import com.adarsh.backend.feature.vendor.presentation.constant.apiconstant.AdminVendorControllerConstants;
import com.adarsh.backend.feature.vendor.presentation.constant.logconstant.VendorControllerLogConstants;
import com.adarsh.backend.feature.vendor.presentation.constant.successmessageconstant.AdminVendorSuccessMessageConstants;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AdminVendorControllerConstants.BASE_PATH)
public class AdminVendorApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(AdminVendorApplicationController.class);

    private final GetVendorApplicationsUseCase getVendorApplicationsUseCase;
    private final ApproveVendorApplicationUseCase approveVendorApplicationUseCase;
    private final RejectVendorApplicationUseCase rejectVendorApplicationUseCase;

    @GetMapping(AdminVendorControllerConstants.GET_ALL_PATH)
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResult<GetVendorApplicationsResult>> getVendorApplications(@RequestParam(required = false) String search, @RequestParam(required = false) ApplicationStatus status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        logger.info(VendorControllerLogConstants.GET_ALL_REQUEST, search, status, page, size);
        PageResult<GetVendorApplicationsResult> result = getVendorApplicationsUseCase.execute(search, status, page, size);
        logger.info(VendorControllerLogConstants.GET_ALL_SUCCESS);
        return ResponseEntity.ok(result);
    }

    @PostMapping(AdminVendorControllerConstants.APPROVE_PATH)
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        logger.info(VendorControllerLogConstants.APPROVE_REQUEST, id);
        approveVendorApplicationUseCase.execute(id);
        logger.info(VendorControllerLogConstants.APPROVE_SUCCESS, id);
        return ResponseEntity.ok(AdminVendorSuccessMessageConstants.APPLICATION_APPROVED);
    }

    @PostMapping(AdminVendorControllerConstants.REJECT_PATH)
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> reject(@PathVariable Long id, @RequestBody RejectVendorApplicationCommand command) {
        logger.info(VendorControllerLogConstants.REJECT_REQUEST, id);
        rejectVendorApplicationUseCase.execute(id, command);
        logger.info(VendorControllerLogConstants.REJECT_SUCCESS, id);
        return ResponseEntity.ok(AdminVendorSuccessMessageConstants.APPLICATION_REJECTED);
    }
}
