package com.adarsh.backend.feature.wishlist.application.interactor;

import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.wishlist.application.interactor.constant.WishlistInteractorLogConstants;
import com.adarsh.backend.feature.wishlist.application.port.WishlistItemCommandRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.port.WishlistItemQueryRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.port.WishlistQueryRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.usecase.DeleteWishlistItemUseCase;
import com.adarsh.backend.feature.wishlist.domain.exception.WishlistAccessDeniedException;
import com.adarsh.backend.feature.wishlist.domain.exception.WishlistItemNotFoundException;
import com.adarsh.backend.feature.wishlist.domain.exception.WishlistNotFoundException;
import com.adarsh.backend.feature.wishlist.domain.exception.constant.WishlistExceptionMessageConstants;
import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;
import com.adarsh.backend.feature.wishlist.domain.model.WishlistItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteWishlistItemInteractor implements DeleteWishlistItemUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteWishlistItemInteractor.class);

    private final UserQueryRepository userQueryRepository;
    private final WishlistQueryRepositoryPort wishlistQueryRepositoryPort;
    private final WishlistItemCommandRepositoryPort wishlistItemCommandRepositoryPort;
    private final WishlistItemQueryRepositoryPort wishlistItemQueryRepositoryPort;

    @Override
    @Transactional
    public void execute(String email, Long wishlistItemId) {
        logger.info(WishlistInteractorLogConstants.DELETE_WISHLIST_ITEM_REQUEST, wishlistItemId, email);

        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Wishlist wishlist = wishlistQueryRepositoryPort.findByUserId(user.getId()).orElseThrow(() -> new WishlistNotFoundException(WishlistExceptionMessageConstants.WISHLIST_NOT_FOUND));

        WishlistItem wishlistItem = wishlistItemQueryRepositoryPort.findById(wishlistItemId).orElseThrow(() -> new WishlistItemNotFoundException(WishlistExceptionMessageConstants.WISHLIST_ITEM_NOT_FOUND));
        logger.debug(WishlistInteractorLogConstants.DELETE_WISHLIST_ITEM_FOUND, wishlistItem.getId());

        if (!wishlistItem.getWishlistId().equals(wishlist.getId())) {
            throw new WishlistAccessDeniedException(WishlistExceptionMessageConstants.WISHLIST_ACCESS_DENIED);
        }

        wishlistItemCommandRepositoryPort.deleteByWishlistItemIdAndWishlistId(wishlistItemId, wishlist.getId());
        logger.info(WishlistInteractorLogConstants.DELETE_WISHLIST_ITEM_DELETED, wishlistItemId);
    }
}
