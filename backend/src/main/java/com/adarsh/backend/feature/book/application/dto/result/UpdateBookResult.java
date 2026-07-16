package com.adarsh.backend.feature.book.application.dto.result;

import com.adarsh.backend.feature.book.domain.model.Book;
import com.adarsh.backend.feature.category.domain.model.CategoryType;

import java.time.LocalDateTime;

public record UpdateBookResult(Long id, LocalDateTime createdAt, String title, String slug, String author,
                               String description, String isbn, CategoryType category, Double price, int stock,
                               String language, int pages) {
    public static UpdateBookResult fromDomain(Book book) {
        return new UpdateBookResult(book.getId(), book.getCreatedAt(), book.getTitle(), book.getSlug(), book.getAuthor()
                , book.getDescription(), book.getIsbn(), book.getCategory(), book.getPrice(), book.getStock(),
                                    book.getLanguage(), book.getPages());
    }
}
