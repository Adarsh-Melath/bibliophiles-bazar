package com.adarsh.backend.feature.wishlist.application.interactor;

import com.adarsh.backend.feature.book.application.port.BookQueryRepositoryPort;
import com.adarsh.backend.feature.book.domain.exception.BookNotFoundException;
import com.adarsh.backend.feature.book.domain.exception.constant.BookExceptionMessageConstants;
import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.wishlist.application.dto.result.GetWishlistItemsResult;
import com.adarsh.backend.feature.wishlist.application.interactor.constant.WishlistInteractorLogConstants;
import com.adarsh.backend.feature.wishlist.application.port.WishlistQueryRepositoryPort;
import com.adarsh.backend.feature.wishlist.application.usecase.GetWishlistItemsUseCase;
import com.adarsh.backend.feature.wishlist.domain.model.Wishlist;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetWishlistItemsInteractor implements GetWishlistItemsUseCase {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(GetWishlistItemsInteractor.class);
    private static final String EMPTY_STRING = "";

    private final UserQueryRepository userQueryRepository;
    private final WishlistQueryRepositoryPort wishlistQueryRepositoryPort;
    private final BookQueryRepositoryPort bookQueryRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<GetWishlistItemsResult> execute(String email) {
        logger.info(WishlistInteractorLogConstants.GET_WISHLIST_ITEMS_REQUEST, email);

        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Wishlist wishlist = wishlistQueryRepositoryPort.findByUserId(user.getId()).orElse(null);
        if (wishlist == null || wishlist.getItems() == null) {
            logger.info(WishlistInteractorLogConstants.GET_WISHLIST_ITEMS_FETCHED, 0);
            return Collections.emptyList();
        }

        logger.debug(WishlistInteractorLogConstants.GET_WISHLIST_ITEMS_WISHLIST_FOUND, wishlist.getId(), user.getId());

        List<GetWishlistItemsResult> results = wishlist.getItems().stream()
                .map(item -> {
                    Book book = bookQueryRepositoryPort.findById(item.getBookId())
                            .orElseThrow(() -> new BookNotFoundException(BookExceptionMessageConstants.BOOK_NOT_FOUND));
                    return new GetWishlistItemsResult(
                            item.getId(),
                            wishlist.getId(),
                            book.getId(),
                            book.getTitle(),
                            book.getAuthor(),
                            book.getImages(),
                            book.getPrice(),
                            book.getSlug(),
                            book.getStock(),
                            book.getCategory() != null ? book.getCategory().name() : EMPTY_STRING,
                            Boolean.TRUE.equals(book.getDeleted())
                    );
                }).collect(Collectors.toList());

        logger.info(WishlistInteractorLogConstants.GET_WISHLIST_ITEMS_FETCHED, results.size());
        return results;
    }
}
