package com.adarsh.backend.feature.order.application.port;

import com.adarsh.backend.feature.order.domain.model.Order;

public interface PdfGenerationPort {
    byte[] generateInvoicePdf(Order order);
}
