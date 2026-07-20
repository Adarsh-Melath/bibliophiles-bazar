package com.adarsh.backend.feature.wishlist.application.dto.command;

import com.adarsh.backend.feature.wishlist.application.dto.command.constant.WishlistValidationConstants;
import java.util.Objects;

public record AddWishlistItemCommand(String slug) {
    public AddWishlistItemCommand {
        Objects.requireNonNull(slug, WishlistValidationConstants.SLUG_CANNOT_BE_NULL);
    }
}
