package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.AdminDeleteOfferUseCase;
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
public class AdminDeleteOfferInteractor implements AdminDeleteOfferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(AdminDeleteOfferInteractor.class);

    private final OfferQueryRepositoryPort offerQueryRepositoryPort;
    private final OfferCommandRepositoryPort offerCommandRepositoryPort;

    @Override
    @Transactional
    public void execute(Long offerId) {
        logger.info(DiscountInteractorLogConstants.DELETE_OFFER_REQUEST, offerId, "ADMIN");

        Offer existing = offerQueryRepositoryPort.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException(String.format(DiscountExceptionMessageConstants.OFFER_NOT_FOUND, offerId)));

        offerCommandRepositoryPort.deleteById(existing.getId());
        logger.info(DiscountInteractorLogConstants.DELETE_OFFER_SUCCESS, offerId);
    }
}
