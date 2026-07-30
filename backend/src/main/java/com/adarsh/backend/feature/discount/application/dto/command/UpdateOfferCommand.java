package com.adarsh.backend.feature.discount.application.dto.command;

import java.time.LocalDateTime;

public record UpdateOfferCommand(String name, Double discountPercentage, LocalDateTime startDate,
                                 LocalDateTime endDate, Boolean isActive) {
}
