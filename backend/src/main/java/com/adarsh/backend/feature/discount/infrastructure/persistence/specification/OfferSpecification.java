package com.adarsh.backend.feature.discount.infrastructure.persistence.specification;

import com.adarsh.backend.feature.discount.application.port.OfferSearchCriteria;
import com.adarsh.backend.feature.discount.infrastructure.persistence.constant.DiscountPersistenceConstants;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.OfferEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class OfferSpecification {

    private OfferSpecification() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static Specification<OfferEntity> build(OfferSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            applyOfferTypeFilter(root, cb, criteria, predicates);
            applyTargetFilter(root, cb, criteria, predicates);
            applyPublisherFilter(root, cb, criteria, predicates);
            applyActiveFilter(root, cb, criteria, predicates);
            applyKeywordFilter(root, cb, criteria, predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void applyOfferTypeFilter(Root<OfferEntity> root, CriteriaBuilder cb, OfferSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getOfferType() != null) {
            predicates.add(cb.equal(root.get(DiscountPersistenceConstants.OFFER_FIELD_OFFER_TYPE), criteria.getOfferType()));
        }
    }

    private static void applyTargetFilter(Root<OfferEntity> root, CriteriaBuilder cb, OfferSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getTargetId() != null) {
            predicates.add(cb.equal(root.get(DiscountPersistenceConstants.OFFER_FIELD_TARGET_ID), criteria.getTargetId()));
        }
    }

    private static void applyPublisherFilter(Root<OfferEntity> root, CriteriaBuilder cb, OfferSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getPublisherId() != null) {
            predicates.add(cb.equal(root.get(DiscountPersistenceConstants.OFFER_FIELD_PUBLISHER_ID), criteria.getPublisherId()));
        }
    }

    private static void applyActiveFilter(Root<OfferEntity> root, CriteriaBuilder cb, OfferSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getIsActive() != null) {
            predicates.add(cb.equal(root.get(DiscountPersistenceConstants.OFFER_FIELD_IS_ACTIVE), criteria.getIsActive()));
        }
    }

    private static void applyKeywordFilter(Root<OfferEntity> root, CriteriaBuilder cb, OfferSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getKeyword() != null && !criteria.getKeyword().trim().isEmpty()) {
            String pattern = "%" + criteria.getKeyword().trim().toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get(DiscountPersistenceConstants.OFFER_FIELD_NAME)), pattern));
        }
    }
}
