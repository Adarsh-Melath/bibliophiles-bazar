package com.adarsh.backend.feature.wishlist.application.usecase;

import com.adarsh.backend.feature.wishlist.application.dto.command.AddWishlistItemCommand;
import com.adarsh.backend.feature.wishlist.application.dto.result.AddWishlistItemResult;

public interface AddWishlistItemUseCase {
    AddWishlistItemResult execute(String email, AddWishlistItemCommand command);
}
