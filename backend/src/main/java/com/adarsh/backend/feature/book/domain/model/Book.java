package com.adarsh.backend.feature.book.domain.model;

import com.adarsh.backend.feature.category.domain.model.CategoryType;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class Book {
    private final Long id;
    private final Long publisherId;
    private final LocalDateTime createdAt;
    private final String slug;
    private final String isbn;
    private String title;
    private String author;
    private String description;
    private CategoryType category;
    private Double price;
    private int stock;
    private String language;
    private int pages;
    private final List<BookImage> images;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    public Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.slug = builder.slug;
        this.author = builder.author;
        this.description = builder.description;
        this.isbn = builder.isbn;
        this.category = builder.category;
        this.publisherId = builder.publisherId;
        this.price = builder.price;
        this.stock = builder.stock;
        this.language = builder.language;
        this.pages = builder.pages;
        this.images = builder.images;
        this.createdAt = LocalDateTime.now(Clock.systemDefaultZone());
        this.updatedAt = builder.updatedAt != null ? builder.updatedAt : LocalDateTime.now(Clock.systemDefaultZone());
        this.deleted = builder.deleted != null ? builder.deleted : false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CategoryType getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public List<BookImage> getImages() {
        return images;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getStock() {
        return stock;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getLanguage() {
        return language;
    }

    public int getPages() {
        return pages;
    }

    public Double getPrice() {
        return price;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public String getSlug() {
        return slug;
    }

    public void updateBook(String title, String author, String description, CategoryType categoryType, Double price, int stock, String language, int pages) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.category = categoryType;
        this.price = price;
        this.stock = stock;
        this.language = language;
        this.pages = pages;
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.updatedAt = LocalDateTime.now();
    }

    public static class Builder {
        private Long id;
        private String title;
        private String slug;
        private String author;
        private String description;
        private String isbn;
        private CategoryType category;
        private Long publisherId;
        private Double price;
        private int stock;
        private String language;
        private int pages;
        private List<BookImage> images;
        private LocalDateTime updatedAt;
        private Boolean deleted;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder category(CategoryType category) {
            this.category = category;
            return this;
        }

        public Builder publisherId(Long publisherId) {
            this.publisherId = publisherId;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder pages(int pages) {
            this.pages = pages;
            return this;
        }

        public Builder images(List<BookImage> images) {
            this.images = images;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder deleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}