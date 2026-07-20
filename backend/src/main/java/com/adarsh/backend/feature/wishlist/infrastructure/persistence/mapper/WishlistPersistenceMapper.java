package com.adarsh.backend.feature.wishlist.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.entity.WishlistEntity;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.entity.WishlistItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {WishlistItemPersistenceMapper.class})
public interface WishlistPersistenceMapper {

    Wishlist toDomain(WishlistEntity entity);

    @Mapping(target = "items", ignore = true)
    WishlistEntity toEntity(Wishlist domain);

    default WishlistEntity toEntityWithItems(Wishlist domain) {
        if (domain == null) {
            return null;
        }
        WishlistEntity entity = toEntity(domain);
        if (domain.getItems() != null) {
            entity.setItems(domain.getItems().stream().map(item -> {
                WishlistItemEntity itemEntity = new WishlistItemEntity();
                itemEntity.setId(item.getId());
                itemEntity.setBookId(item.getBookId());
                itemEntity.setCreatedAt(item.getCreatedAt());
                itemEntity.setWishlist(entity);
                return itemEntity;
            }).collect(Collectors.toCollection(ArrayList::new)));
        }
        return entity;
    }
}
