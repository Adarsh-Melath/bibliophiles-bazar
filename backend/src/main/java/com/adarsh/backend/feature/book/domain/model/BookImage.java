package com.adarsh.backend.feature.book.domain.model;

import java.time.LocalDateTime;

public class BookImage {
    private final Long id;
    private final Long product_id;
    private final String url;
    private final LocalDateTime created_at;


    public BookImage(Builder builder) {
        this.id = builder.id;
        this.product_id = builder.product_id;
        this.url = builder.url;
        this.created_at = builder.created_at;
    }

    public static Builder builder() {
        return new Builder();
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public Long getProduct_id() {
        return product_id;
    }


    public String getUrl() {
        return url;
    }

    public Long getId() {
        return id;
    }

    public static class Builder {
        private Long id;
        private Long product_id;
        private String url;
        private LocalDateTime created_at;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder product_id(Long product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder created_at(LocalDateTime created_at) {
            this.created_at = created_at;
            return this;
        }


        public BookImage build() {
            return new BookImage(this);
        }
    }
}
