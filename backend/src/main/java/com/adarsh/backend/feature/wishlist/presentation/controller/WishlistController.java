package com.adarsh.backend.feature.wishlist.presentation.controller;

import com.adarsh.backend.feature.wishlist.application.dto.command.AddWishlistItemCommand;
import com.adarsh.backend.feature.wishlist.application.dto.result.AddWishlistItemResult;
import com.adarsh.backend.feature.wishlist.application.dto.result.GetWishlistItemsResult;
import com.adarsh.backend.feature.wishlist.application.usecase.AddWishlistItemUseCase;
import com.adarsh.backend.feature.wishlist.application.usecase.DeleteWishlistItemUseCase;
import com.adarsh.backend.feature.wishlist.application.usecase.GetWishlistItemsUseCase;
import com.adarsh.backend.feature.wishlist.presentation.constant.WishlistControllerConstants;
import com.adarsh.backend.feature.wishlist.presentation.constant.WishlistControllerLogConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WishlistControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class WishlistController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(WishlistController.class);

    private final AddWishlistItemUseCase addWishlistItemUseCase;
    private final DeleteWishlistItemUseCase deleteWishlistItemUseCase;
    private final GetWishlistItemsUseCase getWishlistItemsUseCase;

    @PostMapping
    public ResponseEntity<AddWishlistItemResult> addWishlistItem(Authentication authentication, @Valid @RequestBody AddWishlistItemCommand command) {
        String email = authentication.getName();

        logger.info(WishlistControllerLogConstants.ADD_WISHLIST_ITEM_REQUEST, email);

        AddWishlistItemResult result = addWishlistItemUseCase.execute(email, command);

        logger.info(WishlistControllerLogConstants.ADD_WISHLIST_ITEM_SUCCESS, result.itemId());

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<GetWishlistItemsResult>> getWishlistItems(Authentication authentication) {
        String email = authentication.getName();

        logger.info(WishlistControllerLogConstants.GET_WISHLIST_ITEMS_REQUEST, email);

        List<GetWishlistItemsResult> results = getWishlistItemsUseCase.execute(email);

        logger.info(WishlistControllerLogConstants.GET_WISHLIST_ITEMS_SUCCESS, results.size());

        return ResponseEntity.ok(results);
    }

    @DeleteMapping(WishlistControllerConstants.WISHLIST_ITEM_PATH)
    public ResponseEntity<Void> deleteWishlistItem(Authentication authentication, @PathVariable Long wishlistItemId) {
        String email = authentication.getName();

        logger.info(WishlistControllerLogConstants.DELETE_WISHLIST_ITEM_REQUEST, wishlistItemId, email);

        deleteWishlistItemUseCase.execute(email, wishlistItemId);

        logger.info(WishlistControllerLogConstants.DELETE_WISHLIST_ITEM_SUCCESS, wishlistItemId);

        return ResponseEntity.noContent().build();
    }
}
