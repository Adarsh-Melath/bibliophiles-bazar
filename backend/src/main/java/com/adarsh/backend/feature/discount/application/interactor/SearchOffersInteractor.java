package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.result.SearchOffersResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferSearchCriteria;
import com.adarsh.backend.feature.discount.application.usecase.SearchOffersUseCase;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchOffersInteractor implements SearchOffersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SearchOffersInteractor.class);

    private final OfferQueryRepositoryPort offerQueryRepositoryPort;

    @Override
    public PageResult<SearchOffersResult> execute(String keyword, OfferType offerType, Long targetId, Long publisherId, Boolean isActive, int page, int size) {
        logger.info(DiscountInteractorLogConstants.SEARCH_OFFERS_REQUEST, keyword, offerType, publisherId, page, size);

        PageQuery pageQuery = new PageQuery(page, size);
        OfferSearchCriteria criteria = new OfferSearchCriteria.Builder()
                .keyword(keyword)
                .offerType(offerType)
                .targetId(targetId)
                .publisherId(publisherId)
                .isActive(isActive)
                .build();

        PageResult<Offer> domainPage = offerQueryRepositoryPort.search(pageQuery, criteria);
        return domainPage.map(SearchOffersResult::fromDomain);
    }
}
