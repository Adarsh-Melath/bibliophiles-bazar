package com.adarsh.backend.feature.cart.presentation.controller;

import com.adarsh.backend.feature.cart.application.dto.command.AddCartItemCommand;
import com.adarsh.backend.feature.cart.application.dto.result.AddCartItemResult;
import com.adarsh.backend.feature.cart.application.dto.result.GetCartItemsResult;
import com.adarsh.backend.feature.cart.application.usecase.AddCartItemUseCase;
import com.adarsh.backend.feature.cart.application.usecase.DeleteCartItemUseCase;
import com.adarsh.backend.feature.cart.application.usecase.GetCartItemsUseCase;
import com.adarsh.backend.feature.cart.presentation.constant.CartControllerConstants;
import com.adarsh.backend.feature.cart.presentation.constant.CartControllerLogConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CartControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class CartController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CartController.class);

    private final AddCartItemUseCase addCartItemUseCase;
    private final DeleteCartItemUseCase deleteCartItemUseCase;
    private final GetCartItemsUseCase getCartItemsUseCase;

    @PostMapping
    public ResponseEntity<AddCartItemResult> addCartItem(Authentication authentication, @Valid @RequestBody AddCartItemCommand command) {
        String email = authentication.getName();

        logger.info(CartControllerLogConstants.ADD_CART_ITEM_REQUEST, email);

        AddCartItemResult result = addCartItemUseCase.execute(email, command);

        logger.info(CartControllerLogConstants.ADD_CART_ITEM_SUCCESS, result.id());

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<GetCartItemsResult>> getCartItems(Authentication authentication) {
        String email = authentication.getName();

        logger.info(CartControllerLogConstants.GET_CART_ITEMS_REQUEST, email);

        List<GetCartItemsResult> results = getCartItemsUseCase.execute(email);

        logger.info(CartControllerLogConstants.GET_CART_ITEMS_SUCCESS, results.size());

        return ResponseEntity.ok(results);
    }

    @DeleteMapping(CartControllerConstants.CART_ITEM_PATH)
    public ResponseEntity<Void> deleteCartItem(Authentication authentication, @PathVariable Long cartItemId) {
        String email = authentication.getName();

        logger.info(CartControllerLogConstants.DELETE_CART_ITEM_REQUEST, cartItemId, email);

        deleteCartItemUseCase.execute(email, cartItemId);

        logger.info(CartControllerLogConstants.DELETE_CART_ITEM_SUCCESS, cartItemId);

        return ResponseEntity.noContent().build();
    }
}
