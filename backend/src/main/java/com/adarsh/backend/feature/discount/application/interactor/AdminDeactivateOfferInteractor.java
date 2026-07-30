package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.AdminDeactivateOfferUseCase;
import com.adarsh.backend.feature.discount.domain.exception.OfferNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDeactivateOfferInteractor implements AdminDeactivateOfferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AdminDeactivateOfferInteractor.class);

    private final OfferQueryRepositoryPort offerQueryRepositoryPort;
    private final OfferCommandRepositoryPort offerCommandRepositoryPort;

    @Override
    @Transactional
    public void execute(Long offerId) {
        logger.info(DiscountInteractorLogConstants.DEACTIVATE_OFFER_REQUEST, offerId, "ADMIN");

        Offer existingOffer = offerQueryRepositoryPort.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException(String.format(DiscountExceptionMessageConstants.OFFER_NOT_FOUND, offerId)));

        if (!existingOffer.isActive()) {
            return;
        }

        Offer deactivatedOffer = new Offer.Builder()
                .id(existingOffer.getId())
                .name(existingOffer.getName())
                .offerType(existingOffer.getOfferType())
                .targetId(existingOffer.getTargetId())
                .publisherId(existingOffer.getPublisherId())
                .discountPercentage(existingOffer.getDiscountPercentage())
                .startDate(existingOffer.getStartDate())
                .endDate(existingOffer.getEndDate())
                .isActive(false)
                .build();

        offerCommandRepositoryPort.save(deactivatedOffer);
        logger.info(DiscountInteractorLogConstants.DEACTIVATE_OFFER_SUCCESS, offerId);
    }
}
