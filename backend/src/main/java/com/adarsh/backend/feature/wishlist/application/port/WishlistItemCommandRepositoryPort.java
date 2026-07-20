package com.adarsh.backend.feature.wishlist.application.port;

public interface WishlistItemCommandRepositoryPort {
    void deleteByWishlistItemIdAndWishlistId(Long itemId, Long wishlistId);
}
