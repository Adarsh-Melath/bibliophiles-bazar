package com.adarsh.backend.feature.order.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemPersistenceMapper.class})
public interface OrderPersistenceMapper {

    @Mapping(target = "shippingFullName", source = "addressSnapshot.fullName")
    @Mapping(target = "shippingPhone", source = "addressSnapshot.phone")
    @Mapping(target = "shippingAddressLine", source = "addressSnapshot.addressLine")
    @Mapping(target = "shippingAddressLine2", source = "addressSnapshot.addressLine2")
    @Mapping(target = "shippingCity", source = "addressSnapshot.city")
    @Mapping(target = "shippingState", source = "addressSnapshot.state")
    @Mapping(target = "shippingPincode", source = "addressSnapshot.pincode")
    @Mapping(target = "shippingCountry", source = "addressSnapshot.country")
    @Mapping(target = "shippingAddressType", source = "addressSnapshot.addressType")
    @Mapping(target = "items", ignore = true)
    OrderEntity toEntity(Order domain);

    default OrderEntity toEntityWithItems(Order domain) {
        OrderEntity entity = toEntity(domain);
        if (domain.getOrderItemList() != null) {
            java.util.List<OrderItemEntity> itemEntities = domain.getOrderItemList().stream().map(item -> {
                OrderItemEntity itemEntity = new OrderItemEntity();
                itemEntity.setId(item.getId());
                itemEntity.setOrder(entity);
                itemEntity.setBookId(item.getBookId());
                itemEntity.setBookTitle(item.getBookTitle());
                itemEntity.setAuthor(item.getAuthor());
                itemEntity.setIsbn(item.getIsbn());
                itemEntity.setQuantity(item.getQuantity());
                itemEntity.setUnitPrice(item.getUnitPrice());
                itemEntity.setTotalPrice(item.getTotalPrice());
                itemEntity.setStatus(item.getStatus());
                return itemEntity;
            }).toList();
            entity.setItems(itemEntities);
        }
        return entity;
    }

    @Mapping(target = "addressSnapshot.fullName", source = "shippingFullName")
    @Mapping(target = "addressSnapshot.phone", source = "shippingPhone")
    @Mapping(target = "addressSnapshot.addressLine", source = "shippingAddressLine")
    @Mapping(target = "addressSnapshot.addressLine2", source = "shippingAddressLine2")
    @Mapping(target = "addressSnapshot.city", source = "shippingCity")
    @Mapping(target = "addressSnapshot.state", source = "shippingState")
    @Mapping(target = "addressSnapshot.pincode", source = "shippingPincode")
    @Mapping(target = "addressSnapshot.country", source = "shippingCountry")
    @Mapping(target = "addressSnapshot.addressType", source = "shippingAddressType")
    @Mapping(target = "addressSnapshot.id", ignore = true)
    @Mapping(target = "addressSnapshot.userId", ignore = true)
    @Mapping(target = "addressSnapshot.isDefault", ignore = true)
    @Mapping(target = "orderItemList", source = "items")
    Order toDomain(OrderEntity entity);
}
