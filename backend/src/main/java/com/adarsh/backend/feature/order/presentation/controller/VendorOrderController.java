package com.adarsh.backend.feature.order.presentation.controller;

import com.adarsh.backend.feature.order.application.dto.result.SearchVendorOrdersResult;
import com.adarsh.backend.feature.order.application.usecase.SearchVendorOrdersUseCase;
import com.adarsh.backend.feature.order.domain.model.OrderItemStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
import com.adarsh.backend.feature.order.presentation.constant.apiconstant.OrderControllerApiConstants;
import com.adarsh.backend.feature.order.presentation.constant.logconstant.OrderControllerLogConstants;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(OrderControllerApiConstants.VENDOR_ORDERS_BASE_PATH)
@RequiredArgsConstructor
public class VendorOrderController {

    private static final Logger logger = LoggerFactory.getLogger(VendorOrderController.class);
    private final SearchVendorOrdersUseCase searchVendorOrdersUseCase;

    @GetMapping
    public ResponseEntity<PageResult<SearchVendorOrdersResult>> searchVendorOrders(Authentication authentication, @RequestParam(required = false) String keyword, @RequestParam(required = false) OrderItemStatus itemStatus, @RequestParam(required = false) PaymentStatus paymentStatus, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, @RequestParam(defaultValue = OrderControllerApiConstants.DEFAULT_PAGE) int page, @RequestParam(defaultValue = OrderControllerApiConstants.DEFAULT_SIZE) int size, @RequestParam(defaultValue = OrderControllerApiConstants.DEFAULT_SORT_OPTION) OrderSortOption sortOption) {
        String email = authentication.getName();
        logger.info(OrderControllerLogConstants.VENDOR_SEARCH_ORDERS_REQUEST, email, itemStatus, page, size);

        PageResult<SearchVendorOrdersResult> result = searchVendorOrdersUseCase.execute(email, keyword, itemStatus, paymentStatus, startDate, endDate, page, size, sortOption);

        logger.info(OrderControllerLogConstants.VENDOR_SEARCH_ORDERS_SUCCESS, email, result.totalElements());
        return ResponseEntity.ok(result);
    }
}
