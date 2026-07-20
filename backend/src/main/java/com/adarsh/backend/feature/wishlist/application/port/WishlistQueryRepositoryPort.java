package com.adarsh.backend.feature.wishlist.application.port;

import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;

import java.util.Optional;

public interface WishlistQueryRepositoryPort {
    Optional<Wishlist> findByUserId(Long userId);
}
