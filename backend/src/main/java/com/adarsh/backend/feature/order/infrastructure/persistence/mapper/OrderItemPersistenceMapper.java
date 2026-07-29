package com.adarsh.backend.feature.order.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.order.domain.model.OrderItem;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderItemEntity;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemPersistenceMapper {

    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "order", source = "orderEntity")
    OrderItemEntity toEntity(OrderItem domain, OrderEntity orderEntity);

    @Mapping(target = "orderId", source = "order.id")
    OrderItem toDomain(OrderItemEntity entity);
}
