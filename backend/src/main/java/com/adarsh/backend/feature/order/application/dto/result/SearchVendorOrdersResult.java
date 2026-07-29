package com.adarsh.backend.feature.order.application.dto.result;

import com.adarsh.backend.feature.order.domain.model.Order;
import com.adarsh.backend.feature.order.domain.model.OrderItem;
import com.adarsh.backend.feature.order.domain.model.OrderStatus;
import com.adarsh.backend.feature.order.domain.model.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;

public record SearchVendorOrdersResult(Long id, String orderNumber, PaymentStatus paymentStatus,
                                       OrderStatus orderStatus, Double vendorEarnings,
                                       LocalDateTime createdAt, List<VendorOrderItemResult> items) {
    public record VendorOrderItemResult(Long bookId, String bookTitle, int quantity,
                                        Double unitPrice, Double totalPrice) {
    }

    public static SearchVendorOrdersResult fromDomain(Order order, List<Long> vendorBookIds) {
        List<OrderItem> vendorItems = order.getOrderItemList().stream().filter(item -> vendorBookIds.contains(item.getBookId())).toList();

        double vendorEarnings = vendorItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();

        List<VendorOrderItemResult> itemResults = vendorItems.stream().map(item -> new VendorOrderItemResult(item.getBookId(), item.getBookTitle(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice())).toList();

        return new SearchVendorOrdersResult(order.getId(), order.getOrderNumber(), order.getPaymentStatus(), order.getOrderStatus(), vendorEarnings, order.getCreatedAt(), itemResults);
    }
}
