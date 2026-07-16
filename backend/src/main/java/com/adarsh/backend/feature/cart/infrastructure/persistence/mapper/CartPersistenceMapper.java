package com.adarsh.backend.feature.cart.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.cart.domain.model.Cart;
import com.adarsh.backend.feature.cart.infrastructure.persistence.entity.CartEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartPersistenceMapper {

    private final CartItemPersistenceMapper cartItemMapper;

    public CartPersistenceMapper(CartItemPersistenceMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartEntity toEntity(Cart domain) {
        if (domain == null) {
            return null;
        }

        CartEntity entity = new CartEntity();
        entity.setId(domain.getId());
        entity.setUserId(domain.getUserId());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        if (domain.getItems() != null) {
            entity.setItems(domain.getItems().stream().map(item -> cartItemMapper.toEntity(item, entity)).collect(Collectors.toList()));
        }

        return entity;
    }

    public Cart toDomain(CartEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Cart.Builder().id(entity.getId()).userId(entity.getUserId()).items(entity.getItems() != null ? entity.getItems().stream().map(cartItemMapper::toDomain).collect(Collectors.toList()) : null).build();
    }
}
