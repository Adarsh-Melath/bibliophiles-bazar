package com.adarsh.backend.feature.discount.application.port;

import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.List;
import java.util.Optional;

public interface OfferQueryRepositoryPort {

    Optional<Offer> findById(Long id);

    Optional<Offer> findByIdAndPublisherId(Long id, Long publisherId);

    PageResult<Offer> search(PageQuery pageQuery, OfferSearchCriteria criteria);

    List<Offer> findActiveOffersByTargetIdAndType(Long targetId, OfferType offerType);

    List<Offer> findActiveOffersForBookAndCategory(Long bookId, Long categoryId);
}
