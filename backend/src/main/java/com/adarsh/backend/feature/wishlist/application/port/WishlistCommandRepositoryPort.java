package com.adarsh.backend.feature.wishlist.application.port;

import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;

public interface WishlistCommandRepositoryPort {
    Wishlist save(Wishlist wishlist);
}
