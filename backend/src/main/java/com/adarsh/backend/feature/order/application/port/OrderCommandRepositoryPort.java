package com.adarsh.backend.feature.order.application.port;

import com.adarsh.backend.feature.order.domain.model.Order;

public interface OrderCommandRepositoryPort {
    Order save(Order order);
}
