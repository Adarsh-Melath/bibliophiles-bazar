package com.adarsh.backend.feature.wishlist.application.usecase;

import com.adarsh.backend.feature.wishlist.application.dto.result.GetWishlistItemsResult;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.List;

public interface GetWishlistItemsUseCase {
    List<GetWishlistItemsResult> execute(String email);
}
