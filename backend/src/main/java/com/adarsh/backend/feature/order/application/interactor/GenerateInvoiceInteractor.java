package com.adarsh.backend.feature.order.application.interactor;

import com.adarsh.backend.feature.order.application.port.OrderQueryRepositoryPort;
import com.adarsh.backend.feature.order.application.port.PdfGenerationPort;
import com.adarsh.backend.feature.order.application.usecase.GenerateInvoiceUseCase;
import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.adarsh.backend.feature.order.domain.exception.OrderNotFoundException;
import com.adarsh.backend.feature.order.domain.exception.constant.OrderExceptionMessageConstants;
import com.adarsh.backend.feature.order.application.interactor.constant.OrderInteractorLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class GenerateInvoiceInteractor implements GenerateInvoiceUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GenerateInvoiceInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final OrderQueryRepositoryPort orderQueryRepositoryPort;
    private final PdfGenerationPort pdfGenerationPort;

    public byte[] execute(String email, Long orderId) {
        logger.info(OrderInteractorLogConstants.GENERATE_INVOICE_REQUEST, orderId, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(OrderInteractorLogConstants.GENERATE_INVOICE_USER_FOUND, user.getId());
        Order order = orderQueryRepositoryPort.findByUserIdAndOrderId(user.getId(), orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format(OrderExceptionMessageConstants.ORDER_NOT_FOUND_FOR_USER, user.getId(), orderId)));
        logger.debug(OrderInteractorLogConstants.GENERATE_INVOICE_FOUND, order.getId());
        byte[] pdfBytes = pdfGenerationPort.generateInvoicePdf(order);
        logger.info(OrderInteractorLogConstants.GENERATE_INVOICE_SUCCESS, order.getId());
        return pdfBytes;
    }
}
