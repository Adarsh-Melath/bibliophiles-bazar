package com.adarsh.backend.feature.wishlist.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.wishlist.application.port.WishlistItemCommandRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.port.WishlistItemQueryRepositoryPort;
import com.adarsh.backend.feature.wishlist.domain.model.WishlistItem;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.jparepository.WishlistItemJpaRepository;
import com.adarsh.backend.feature.wishlist.infrastructure.persistence.mapper.WishlistItemPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WishlistItemRepositoryAdapter implements WishlistItemCommandRepositoryPort, WishlistItemQueryRepositoryPort {

    private final WishlistItemJpaRepository wishlistItemJpaRepository;
    private final WishlistItemPersistenceMapper wishlistItemPersistenceMapper;

    @Override
    public Optional<WishlistItem> findById(Long wishlistItemId) {
        return wishlistItemJpaRepository.findById(wishlistItemId).map(wishlistItemPersistenceMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteByWishlistItemIdAndWishlistId(Long itemId, Long wishlistId) {
        wishlistItemJpaRepository.deleteByWishlistIdAndWishlistItemId(wishlistId, itemId);
    }
}
