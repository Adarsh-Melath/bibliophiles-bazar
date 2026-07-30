package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.VendorDeactivateOfferUseCase;
import com.adarsh.backend.feature.discount.domain.exception.OfferNotFoundException;
import com.adarsh.backend.feature.discount.domain.exception.UnauthorizedOfferAccessException;
import com.adarsh.backend.feature.discount.domain.exception.constant.DiscountExceptionMessageConstants;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VendorDeactivateOfferInteractor implements VendorDeactivateOfferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(VendorDeactivateOfferInteractor.class);

    private final UserQueryRepository userQueryRepository;
    private final OfferQueryRepositoryPort offerQueryRepositoryPort;
    private final OfferCommandRepositoryPort offerCommandRepositoryPort;

    @Override
    @Transactional
    public void execute(Long offerId, String vendorEmail) {
        logger.info(DiscountInteractorLogConstants.DEACTIVATE_OFFER_REQUEST, offerId, vendorEmail);

        User vendor = userQueryRepository.findByEmail(vendorEmail)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Offer existingOffer = offerQueryRepositoryPort.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException(String.format(DiscountExceptionMessageConstants.OFFER_NOT_FOUND, offerId)));

        if (existingOffer.getPublisherId() == null || !existingOffer.getPublisherId().equals(vendor.getId())) {
            throw new UnauthorizedOfferAccessException(DiscountExceptionMessageConstants.UNAUTHORIZED_OFFER_ACCESS);
        }

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
