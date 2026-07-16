package com.adarsh.backend.feature.book.application.dto.result;

import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.book.domain.model.BookImage;
import com.adarsh.backend.feature.category.domain.model.CategoryType;

import java.time.LocalDateTime;
import java.util.List;

public record GetBooksResult(Long id, Long publisherId, LocalDateTime createdAt, String slug, String isbn, String title,
                             String author, String description, CategoryType type, Double price, int stock,
                             String language, int page, List<BookImage> images) {
    public static GetBooksResult fromDomain(Book book) {
        return new GetBooksResult(book.getId(), book.getPublisherId(), book.getCreatedAt(), book.getSlug(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getCategory(), book.getPrice(), book.getStock(), book.getLanguage(), book.getPages(), book.getImages());
    }
}

