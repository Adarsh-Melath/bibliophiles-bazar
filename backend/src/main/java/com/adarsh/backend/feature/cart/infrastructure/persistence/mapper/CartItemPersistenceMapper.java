package com.adarsh.backend.feature.cart.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.cart.domain.model.CartItem;
import com.adarsh.backend.feature.cart.infrastructure.persistence.entity.CartEntity;
import com.adarsh.backend.feature.cart.infrastructure.persistence.entity.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemPersistenceMapper {

    public CartItemEntity toEntity(CartItem domain, CartEntity cartEntity) {
        if (domain == null) {
            return null;
        }

        CartItemEntity entity = new CartItemEntity();
        entity.setId(domain.getId());
        entity.setCart(cartEntity);
        entity.setBookId(domain.getBookId());
        entity.setQuantity(domain.getQuantity());
        entity.setUnitPrice(domain.getUnitPrice());

        return entity;
    }

    public CartItem toDomain(CartItemEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CartItem.Builder().id(entity.getId()).cartId(entity.getCart() != null ? entity.getCart().getId() : null).bookId(entity.getBookId()).quantity(entity.getQuantity()).unitPrice(entity.getUnitPrice()).build();
    }
}
