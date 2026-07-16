package com.adarsh.backend.feature.book.application.dto.command;

import com.adarsh.backend.feature.category.domain.model.CategoryType;

import java.util.List;
import java.util.Objects;

public record AddBookCommand(String title, String author, String description, String isbn, CategoryType categoryType,
                             Double price, int stock, String language, int pages, List<String> images) {
    public AddBookCommand {
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(author, "Author cannot be null");
        Objects.requireNonNull(description, "Description cannot be null");
        Objects.requireNonNull(isbn, "ISBN cannot be null");
        Objects.requireNonNull(categoryType, "Category type cannot be null");
        Objects.requireNonNull(price, "Price cannot be null");
        Objects.requireNonNull(language, "Language cannot be null");
        Objects.requireNonNull(images, "Images cannot be null");

        if (title.isBlank()) throw new IllegalArgumentException("Title cannot be blank");
        if (author.isBlank()) throw new IllegalArgumentException("Author cannot be blank");
        if (description.isBlank()) throw new IllegalArgumentException("Description cannot be blank");
        if (isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be blank");
        if (language.isBlank()) throw new IllegalArgumentException("Language cannot be blank");
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        if (pages <= 0) throw new IllegalArgumentException("Pages must be greater than 0");
        if (images.isEmpty()) throw new IllegalArgumentException("Images cannot be empty");
    }
}
