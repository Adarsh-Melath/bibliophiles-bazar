package com.adarsh.backend.feature.discount.infrastructure.persistence.specification;

import com.adarsh.backend.feature.discount.application.port.CouponSearchCriteria;
import com.adarsh.backend.feature.discount.infrastructure.persistence.constant.DiscountPersistenceConstants;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.CouponEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class CouponSpecification {

    private CouponSpecification() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static Specification<CouponEntity> build(CouponSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            applyActiveFilter(root, cb, criteria, predicates);
            applyTypeFilter(root, cb, criteria, predicates);
            applyKeywordFilter(root, cb, criteria, predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void applyActiveFilter(Root<CouponEntity> root, CriteriaBuilder cb, CouponSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getIsActive() != null) {
            predicates.add(cb.equal(root.get(DiscountPersistenceConstants.COUPON_FIELD_IS_ACTIVE), criteria.getIsActive()));
        }
    }

    private static void applyTypeFilter(Root<CouponEntity> root, CriteriaBuilder cb, CouponSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getDiscountType() != null) {
            predicates.add(cb.equal(root.get(DiscountPersistenceConstants.COUPON_FIELD_DISCOUNT_TYPE), criteria.getDiscountType()));
        }
    }

    private static void applyKeywordFilter(Root<CouponEntity> root, CriteriaBuilder cb, CouponSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getKeyword() != null && !criteria.getKeyword().trim().isEmpty()) {
            String pattern = "%" + criteria.getKeyword().trim().toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get(DiscountPersistenceConstants.COUPON_FIELD_CODE)), pattern));
        }
    }
}
