package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.category.application.port.CategoryQueryPort;
import com.adarsh.backend.feature.category.domain.exception.CategoryNotFoundException;
import com.adarsh.backend.feature.category.domain.exception.constant.CategoryExceptionConstants;
import com.adarsh.backend.feature.category.domain.model.Category;
import com.adarsh.backend.feature.discount.application.dto.command.CreateCategoryOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateOfferResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorMessageConstants;
import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.CreateCategoryOfferUseCase;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoryOfferInteractor implements CreateCategoryOfferUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateCategoryOfferInteractor.class);

    private final CategoryQueryPort categoryQueryPort;
    private final OfferCommandRepositoryPort offerCommandRepositoryPort;

    @Override
    @Transactional
    public CreateOfferResult execute(CreateCategoryOfferCommand command) {
        logger.info(DiscountInteractorLogConstants.CREATE_CATEGORY_OFFER_REQUEST, command.categoryId());

        Category category = categoryQueryPort.findById(command.categoryId()).orElseThrow(() -> new CategoryNotFoundException(CategoryExceptionConstants.CATEGORY_NOT_FOUND));

        String categoryDisplayName = category.getType() != null ? category.getType().name() : category.getSlug();

        String offerName = command.name() != null && !command.name().trim().isEmpty()
                ? command.name().trim()
                : String.format(DiscountInteractorMessageConstants.DEFAULT_CATEGORY_OFFER_NAME, categoryDisplayName);

        Offer newOffer = new Offer.Builder().name(offerName).offerType(OfferType.CATEGORY).targetId(command.categoryId()).publisherId(null).discountPercentage(command.discountPercentage()).startDate(command.startDate()).endDate(command.endDate()).isActive(true).build();

        Offer savedOffer = offerCommandRepositoryPort.save(newOffer);
        return CreateOfferResult.fromDomain(savedOffer);
    }
}