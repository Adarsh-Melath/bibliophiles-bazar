package com.adarsh.backend.feature.order.presentation.controller;

import com.adarsh.backend.feature.order.application.dto.result.SearchAdminOrdersResult;
import com.adarsh.backend.feature.order.application.usecase.SearchAdminOrdersUseCase;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.PaymentMethod;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
import com.adarsh.backend.feature.order.presentation.constant.apiconstant.OrderControllerApiConstants;
import com.adarsh.backend.feature.order.presentation.constant.logconstant.OrderControllerLogConstants;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(OrderControllerApiConstants.ADMIN_ORDERS_BASE_PATH)
@RequiredArgsConstructor
public class AdminOrderController {

    private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
    private final SearchAdminOrdersUseCase searchAdminOrdersUseCase;

    @GetMapping
    public ResponseEntity<PageResult<SearchAdminOrdersResult>> searchAdminOrders(@RequestParam(required = false) String keyword, @RequestParam(required = false) OrderStatus orderStatus, @RequestParam(required = false) PaymentStatus paymentStatus, @RequestParam(required = false) PaymentMethod paymentMethod, @RequestParam(required = false) Long customerId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, @RequestParam(required = false) Double minGrandTotal, @RequestParam(required = false) Double maxGrandTotal, @RequestParam(defaultValue = OrderControllerApiConstants.DEFAULT_PAGE) int page, @RequestParam(defaultValue = OrderControllerApiConstants.DEFAULT_SIZE) int size, @RequestParam(defaultValue = OrderControllerApiConstants.DEFAULT_SORT_OPTION) OrderSortOption sortOption) {
        logger.info(OrderControllerLogConstants.ADMIN_SEARCH_ORDERS_REQUEST, keyword, orderStatus, customerId, page, size);

        PageResult<SearchAdminOrdersResult> result = searchAdminOrdersUseCase.execute(keyword, orderStatus, paymentStatus, paymentMethod, customerId, startDate, endDate, minGrandTotal, maxGrandTotal, page, size, sortOption);

        logger.info(OrderControllerLogConstants.ADMIN_SEARCH_ORDERS_SUCCESS, result.totalElements());
        return ResponseEntity.ok(result);
    }
}
