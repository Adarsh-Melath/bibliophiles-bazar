package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.command.UpdateOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateOfferResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.VendorUpdateOfferUseCase;
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
public class VendorUpdateOfferInteractor implements VendorUpdateOfferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(VendorUpdateOfferInteractor.class);

    private final UserQueryRepository userQueryRepository;
    private final OfferQueryRepositoryPort offerQueryRepositoryPort;
    private final OfferCommandRepositoryPort offerCommandRepositoryPort;

    @Override
    @Transactional
    public UpdateOfferResult execute(Long offerId, String vendorEmail, UpdateOfferCommand command) {
        logger.info(DiscountInteractorLogConstants.UPDATE_OFFER_REQUEST, offerId, vendorEmail);

        User vendor = userQueryRepository.findByEmail(vendorEmail)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Offer existing = offerQueryRepositoryPort.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException(String.format(DiscountExceptionMessageConstants.OFFER_NOT_FOUND, offerId)));

        if (existing.getPublisherId() == null || !existing.getPublisherId().equals(vendor.getId())) {
            throw new UnauthorizedOfferAccessException(DiscountExceptionMessageConstants.UNAUTHORIZED_OFFER_ACCESS);
        }

        Offer updated = new Offer.Builder()
                .id(existing.getId())
                .name(command.name() != null ? command.name() : existing.getName())
                .offerType(existing.getOfferType())
                .targetId(existing.getTargetId())
                .publisherId(existing.getPublisherId())
                .discountPercentage(command.discountPercentage() != null ? command.discountPercentage() : existing.getDiscountPercentage())
                .startDate(command.startDate() != null ? command.startDate() : existing.getStartDate())
                .endDate(command.endDate() != null ? command.endDate() : existing.getEndDate())
                .isActive(command.isActive() != null ? command.isActive() : existing.isActive())
                .build();

        Offer saved = offerCommandRepositoryPort.save(updated);
        logger.info(DiscountInteractorLogConstants.UPDATE_OFFER_SUCCESS, saved.getId());

        return UpdateOfferResult.fromDomain(saved);
    }
}
