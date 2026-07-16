package com.adarsh.backend.feature.category.domain.model;

import java.time.Clock;
import java.time.LocalDateTime;

public class Category {
    private final Long id;
    private final CategoryType type;
    private final String slug;
    private String description;
    private Boolean deleted;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Category(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.slug = builder.slug;
        this.description = builder.description;
        this.createdAt = LocalDateTime.now(Clock.systemDefaultZone());
        this.deleted = builder.deleted != null ? builder.deleted : false;
    }

    public static Builder builder() {
        return new Builder();
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

    public String getSlug() {
        return slug;
    }

    public CategoryType getType() {
        return type;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public void softDelete() {
        this.deleted = true;
        this.updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }

    public static class Builder {
        private Long id;
        private CategoryType type;
        private String slug;
        private String description;
        private Boolean deleted = false;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(CategoryType type) {
            this.type = type;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder deleted(Boolean deleted) {
            this.deleted = deleted;
            return this;

        }

        public Category build() {
            return new Category(this);
        }
    }
}
