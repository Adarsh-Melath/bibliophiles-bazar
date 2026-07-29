package com.adarsh.backend.feature.order.domain.model;

public class OrderItem {
    private final Long id;
    private final Long orderId;
    private Long bookId;
    private String bookTitle;
    private String author;
    private String isbn;
    private int quantity;
    private Double unitPrice;
    private Double totalPrice;
    private OrderItemStatus status;

    public OrderItem(Builder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.bookId = builder.bookId;
        this.bookTitle = builder.bookTitle;
        this.author = builder.author;
        this.isbn = builder.isbn;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long orderId;
        private Long bookId;
        private String bookTitle;
        private String author;
        private String isbn;
        private int quantity;
        private Double unitPrice;
        private Double totalPrice;
        private OrderItemStatus status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder bookId(Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder bookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;

        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder status(OrderItemStatus status) {
            this.status = status;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

    public void cancelOrderItem() {
        this.status = OrderItemStatus.CANCELLED;
    }

    public void returnOrderItem() {
        this.status = OrderItemStatus.RETURN_REQUESTED;
    }
}
