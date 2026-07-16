package com.adarsh.backend.feature.book.application.dto.command;

import com.adarsh.backend.feature.category.domain.model.CategoryType;

public record UpdateBookCommand(String title, String author, String description, CategoryType categoryType,
                                Double price, int stock, String language, int pages) {
}
