package com.adarsh.backend.feature.discount.application.dto.command;

import java.time.LocalDateTime;

public record CreateProductOfferCommand(Long bookId, String name, Double discountPercentage,
                                        LocalDateTime startDate, LocalDateTime endDate) {
}
