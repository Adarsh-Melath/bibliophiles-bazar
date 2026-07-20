package com.adarsh.backend.feature.wishlist.application.usecase;

public interface DeleteWishlistItemUseCase {
    void execute(String email, Long wishlistItemId);
}
