package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.result.SearchAdminOrdersResult;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.PaymentMethod;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.time.LocalDateTime;

public interface SearchAdminOrdersUseCase {
    PageResult<SearchAdminOrdersResult> execute(
            String keyword,
            OrderStatus orderStatus,
            PaymentStatus paymentStatus,
            PaymentMethod paymentMethod,
            Long customerId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Double minGrandTotal,
            Double maxGrandTotal,
            int page,
            int size,
            OrderSortOption sortOption
    );
}
