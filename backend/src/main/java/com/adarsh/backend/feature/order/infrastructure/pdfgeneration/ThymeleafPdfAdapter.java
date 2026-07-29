package com.adarsh.backend.feature.order.infrastructure.pdfgeneration;

import com.adarsh.backend.feature.order.application.port.PdfGenerationPort;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.adarsh.backend.feature.order.domain.exception.InvoiceGenerationException;
import com.adarsh.backend.feature.order.domain.exception.constant.OrderExceptionMessageConstants;
import com.adarsh.backend.feature.order.infrastructure.pdfgeneration.constant.InvoicePdfConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class ThymeleafPdfAdapter implements PdfGenerationPort {
    private static final Logger logger = LoggerFactory.getLogger(ThymeleafPdfAdapter.class);
    private final TemplateEngine templateEngine;

    @Override
    public byte[] generateInvoicePdf(Order order) {
        logger.info(InvoicePdfConstants.LOG_PDF_START, order.getId(), order.getOrderNumber());
        Context context = new Context();
        context.setVariable(InvoicePdfConstants.VAR_ORDER, order);
        context.setVariable(InvoicePdfConstants.VAR_CUSTOMER_NAME, order.getAddressSnapshot().getFullName());

        String htmlContent = templateEngine.process(InvoicePdfConstants.TEMPLATE_NAME, context);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();

            logger.info(InvoicePdfConstants.LOG_PDF_SUCCESS, order.getId());
            return outputStream.toByteArray();
        } catch (Exception e) {
            logger.error(InvoicePdfConstants.LOG_PDF_ERROR, order.getId(), e);
            throw new InvoiceGenerationException(OrderExceptionMessageConstants.FAILED_TO_GENERATE_INVOICE, e);
        }
    }
}
