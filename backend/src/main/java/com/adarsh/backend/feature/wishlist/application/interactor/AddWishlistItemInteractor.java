package com.adarsh.backend.feature.wishlist.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.wishlist.application.dto.command.AddWishlistItemCommand;
import com.adarsh.backend.feature.wishlist.application.dto.result.AddWishlistItemResult;
import com.adarsh.backend.feature.wishlist.application.interactor.constant.WishlistInteractorLogConstants;
import com.adarsh.backend.feature.wishlist.application.port.WishlistCommandRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.port.WishlistQueryRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.usecase.AddWishlistItemUseCase;
import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;
import com.adarsh.backend.feature.wishlist.domain.model.WishlistItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AddWishlistItemInteractor implements AddWishlistItemUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AddWishlistItemInteractor.class);
    private static final String EMPTY_STRING = "";

    private final UserQueryRepository userQueryRepository;
    private final WishlistQueryRepositoryPort wishlistQueryRepositoryPort;
    private final WishlistCommandRepositoryPort wishlistCommandRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    @Transactional
    public AddWishlistItemResult execute(String email, AddWishlistItemCommand command) {
        logger.info(WishlistInteractorLogConstants.ADD_WISHLIST_ITEM_REQUEST, email, command.slug());

        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));
        logger.debug(WishlistInteractorLogConstants.ADD_WISHLIST_ITEM_USER_FOUND, user.getId());

        Book book = bookQueryRepositoryPort.findBySlug(command.slug()).orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));
        logger.debug(WishlistInteractorLogConstants.ADD_WISHLIST_ITEM_BOOK_FOUND, book.getId());

        Wishlist wishlist = wishlistQueryRepositoryPort.findByUserId(user.getId()).orElseGet(() -> {
            Wishlist newWishlist = Wishlist.builder().userId(user.getId()).items(new ArrayList<>()).build();
            Wishlist savedWishlist = wishlistCommandRepositoryPort.save(newWishlist);
            logger.debug(WishlistInteractorLogConstants.ADD_WISHLIST_ITEM_WISHLIST_CREATED, savedWishlist.getId(), user.getId());
            return savedWishlist;
        });

        if (wishlist.getId() != null) {
            logger.debug(WishlistInteractorLogConstants.ADD_WISHLIST_ITEM_WISHLIST_FOUND, wishlist.getId(), user.getId());
        }

        if (wishlist.getItems() == null) {
            wishlist = Wishlist.builder().id(wishlist.getId()).userId(wishlist.getUserId()).items(new ArrayList<>()).createdAt(wishlist.getCreatedAt()).updatedAt(wishlist.getUpdatedAt()).build();
        }

        if (wishlist.getItems().stream().noneMatch(item -> item.getBookId().equals(book.getId()))) {
            WishlistItem wishlistItem = new WishlistItem.Builder().wishlistId(wishlist.getId()).bookId(book.getId()).build();
            wishlist.addItem(wishlistItem);
        }

        Wishlist updatedWishlist = wishlistCommandRepositoryPort.save(wishlist);
        logger.info(WishlistInteractorLogConstants.ADD_WISHLIST_ITEM_SAVED, book.getId(), wishlist.getId());

        WishlistItem savedItem = updatedWishlist.getItems().stream()
                .filter(item -> item.getBookId().equals(book.getId()))
                .findFirst()
                .orElse(null);
        Long itemId = savedItem != null ? savedItem.getId() : null;

        return new AddWishlistItemResult(
            itemId,
            updatedWishlist.getId(),
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getImages(),
            book.getPrice(),
            book.getSlug(),
            book.getStock(),
            book.getCategory() != null ? book.getCategory().name() : EMPTY_STRING,
            false
        );
    }
}
