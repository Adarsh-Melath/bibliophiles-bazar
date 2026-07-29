package com.adarsh.backend.feature.order.presentation.controller;

import com.adarsh.backend.feature.order.application.usecase.GenerateInvoiceUseCase;
import com.adarsh.backend.feature.order.presentation.constant.apiconstant.OrderControllerApiConstants;
import com.adarsh.backend.feature.order.presentation.constant.logconstant.OrderControllerLogConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrderControllerApiConstants.INVOICE_BASE_PATH)
@RequiredArgsConstructor
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    private final GenerateInvoiceUseCase generateInvoiceUseCase;

    @GetMapping(OrderControllerApiConstants.GET_INVOICE_PATH)
    public ResponseEntity<byte[]> generateInvoice(Authentication authentication, @PathVariable Long orderId) {
        String email = authentication.getName();
        logger.info(OrderControllerLogConstants.INVOICE_REQUEST, orderId, email);
        byte[] pdfBytes = generateInvoiceUseCase.execute(email, orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(OrderControllerApiConstants.INVOICE_ATTACHMENT, OrderControllerApiConstants.INVOICE_FILENAME_PREFIX + orderId + OrderControllerApiConstants.INVOICE_FILE_EXTENSION);
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
