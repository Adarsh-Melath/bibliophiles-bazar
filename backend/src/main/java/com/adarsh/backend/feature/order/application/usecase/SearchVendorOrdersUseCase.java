package com.adarsh.backend.feature.order.application.usecase;

import com.adarsh.backend.feature.order.application.dto.result.SearchVendorOrdersResult;
import com.adarsh.backend.feature.order.domain.model.OrderItemStatus;
import com.adarsh.backend.feature.order.domain.model.OrderSortOption;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.time.LocalDateTime;

public interface SearchVendorOrdersUseCase {
    PageResult<SearchVendorOrdersResult> execute(
            String email,
            String keyword,
            OrderItemStatus itemStatus,
            PaymentStatus paymentStatus,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int page,
            int size,
            OrderSortOption sortOption
    );
}
