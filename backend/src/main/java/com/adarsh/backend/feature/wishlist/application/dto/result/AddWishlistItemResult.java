package com.adarsh.backend.feature.wishlist.application.dto.result;

import com.adarsh.backend.feature.book.domain.model.BookImage;

import java.util.List;

public record AddWishlistItemResult(
    Long itemId,
    Long wishlistId,
    Long bookId,
    String bookTitle,
    String author,
    List<BookImage> images,
    Double price,
    String slug,
    Integer stock,
    String category,
    Boolean deleted
) {
}
