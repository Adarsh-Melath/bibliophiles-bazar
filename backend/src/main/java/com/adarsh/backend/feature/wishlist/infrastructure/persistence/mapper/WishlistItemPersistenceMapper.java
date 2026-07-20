package com.adarsh.backend.feature.wishlist.infrastructure.persistence.mapper;

import com.adarsh.backend.feature.wishlist.domain.model.WishlistItem;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.entity.WishlistItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistItemPersistenceMapper {

    @Mapping(target = "wishlistId", source = "wishlist.id")
    WishlistItem toDomain(WishlistItemEntity entity);

    @Mapping(target = "wishlist", ignore = true)
    WishlistItemEntity toEntity(WishlistItem domain);
}
