package com.adarsh.backend.feature.discount.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.discount.application.port.OfferCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.OfferSearchCriteria;
import com.adarsh.backend.feature.discount.domain.model.Offer;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.feature.discount.infrastructure.persistence.constant.DiscountPersistenceConstants;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.OfferEntity;
import com.adarsh.backend.feature.discount.infrastructure.persistence.jparepository.OfferJpaRepository;
import com.adarsh.backend.feature.discount.infrastructure.persistence.mapper.OfferPersistenceMapper;
import com.adarsh.backend.feature.discount.infrastructure.persistence.specification.OfferSpecification;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OfferRepositoryAdapter implements OfferCommandRepositoryPort, OfferQueryRepositoryPort {

    private final OfferJpaRepository offerJpaRepository;
    private final OfferPersistenceMapper offerPersistenceMapper;

    @Override
    public Offer save(Offer offer) {
        OfferEntity entity = offerPersistenceMapper.toEntity(offer);
        OfferEntity savedEntity = offerJpaRepository.save(entity);
        return offerPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        offerJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Offer> findById(Long id) {
        return offerJpaRepository.findById(id).map(offerPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Offer> findByIdAndPublisherId(Long id, Long publisherId) {
        return offerJpaRepository.findByIdAndPublisherId(id, publisherId).map(offerPersistenceMapper::toDomain);
    }

    @Override
    public PageResult<Offer> search(PageQuery pageQuery, OfferSearchCriteria criteria) {
        Pageable pageable = PageRequest.of(pageQuery.page(), pageQuery.size(), Sort.by(DiscountPersistenceConstants.OFFER_FIELD_ID).descending());
        Specification<OfferEntity> spec = OfferSpecification.build(criteria);
        Page<OfferEntity> springPage = offerJpaRepository.findAll(spec, pageable);
        List<Offer> domainOffers = springPage.getContent().stream().map(offerPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainOffers, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    @Override
    public List<Offer> findActiveOffersByTargetIdAndType(Long targetId, OfferType offerType) {
        List<OfferEntity> entities = offerJpaRepository.findByTargetIdAndOfferTypeAndIsActiveTrue(targetId, offerType);
        return entities.stream().map(offerPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Offer> findActiveOffersForBookAndCategory(Long bookId, Long categoryId) {
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        List<OfferEntity> entities = offerJpaRepository.findActiveOffersForBookAndCategory(bookId, categoryId, now);
        return entities.stream().map(offerPersistenceMapper::toDomain).toList();
    }
}
