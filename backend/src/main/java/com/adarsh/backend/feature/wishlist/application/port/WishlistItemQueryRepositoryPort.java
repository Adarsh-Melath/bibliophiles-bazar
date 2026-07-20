package com.adarsh.backend.feature.wishlist.application.port;

import com.adarsh.backend.feature.wishlist.domain.model.WishlistItem;

import java.util.Optional;

public interface WishlistItemQueryRepositoryPort {
    Optional<WishlistItem> findById(Long wishlistItemId);
}
