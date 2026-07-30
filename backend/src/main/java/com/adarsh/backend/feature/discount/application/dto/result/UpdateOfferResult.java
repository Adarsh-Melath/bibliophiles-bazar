package com.adarsh.backend.feature.discount.application.dto.result;

import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.domain.model.OfferType;

import java.time.LocalDateTime;

public record UpdateOfferResult(Long id, String name, OfferType offerType, Long targetId,
                                Long publisherId, Double discountPercentage,
                                LocalDateTime startDate, LocalDateTime endDate, boolean isActive) {
    public static UpdateOfferResult fromDomain(Offer offer) {
        if (offer == null) {
            return null;
        }
        return new UpdateOfferResult(offer.getId(), offer.getName(), offer.getOfferType(), offer.getTargetId(), offer.getPublisherId(), offer.getDiscountPercentage(), offer.getStartDate(), offer.getEndDate(), offer.isActive());
    }
}
