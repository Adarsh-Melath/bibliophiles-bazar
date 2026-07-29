package com.adarsh.backend.feature.order.application.port;

import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.Optional;

public interface OrderQueryRepositoryPort {
    PageResult<Order> search(PageQuery pageQuery, SearchCustomerOrdersCriteria criteria);

    Optional<Order> findByUserIdAndOrderId(Long userId, Long orderId);
}
