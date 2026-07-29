package com.adarsh.backend.feature.order.infrastructure.persistence.specification;

import com.adarsh.backend.feature.order.application.port.OrderSearchCriteria;
import com.adarsh.backend.feature.order.infrastructure.persistence.constant.OrderPersistenceConstants;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderEntity;
import com.adarsh.backend.feature.order.infrastructure.persistence.entity.OrderItemEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class OrderSpecification {

    private OrderSpecification() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    public static Specification<OrderEntity> build(OrderSearchCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            applyCustomerFilter(root, cb, criteria, predicates);
            applyVendorFilters(root, query, cb, criteria, predicates);
            applyStatusFilters(root, cb, criteria, predicates);
            applyDateRangeFilter(root, cb, criteria, predicates);
            applyGrandTotalFilter(root, cb, criteria, predicates);
            applyKeywordSearch(root, query, cb, criteria, predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void applyCustomerFilter(Root<OrderEntity> root, CriteriaBuilder cb, OrderSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getCustomerId() != null) {
            predicates.add(cb.equal(root.get(OrderPersistenceConstants.FIELD_CUSTOMER_ID), criteria.getCustomerId()));
        }
    }

    /**
     * Handles vendor-scoped filters: allowed book IDs and item status.
     * Both filters share the same INNER JOIN on items to avoid a duplicate SQL join.
     */
    private static void applyVendorFilters(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb, OrderSearchCriteria criteria, List<Predicate> predicates) {
        Join<OrderEntity, OrderItemEntity> itemsJoin = null;

        if (criteria.getAllowedBookIds() != null) {
            if (criteria.getAllowedBookIds().isEmpty()) {
                predicates.add(cb.disjunction());
            } else {
                itemsJoin = root.join(OrderPersistenceConstants.FIELD_ITEMS);
                predicates.add(itemsJoin.get(OrderPersistenceConstants.FIELD_BOOK_ID).in(criteria.getAllowedBookIds()));
                query.distinct(true);
            }
        }

        if (criteria.getItemStatus() != null) {
            if (itemsJoin == null) {
                itemsJoin = root.join(OrderPersistenceConstants.FIELD_ITEMS);
                query.distinct(true);
            }
            predicates.add(cb.equal(itemsJoin.get(OrderPersistenceConstants.FIELD_ITEM_STATUS), criteria.getItemStatus()));
        }
    }

    private static void applyStatusFilters(Root<OrderEntity> root, CriteriaBuilder cb, OrderSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getOrderStatus() != null) {
            predicates.add(cb.equal(root.get(OrderPersistenceConstants.FIELD_ORDER_STATUS), criteria.getOrderStatus()));
        }
        if (criteria.getPaymentStatus() != null) {
            predicates.add(cb.equal(root.get(OrderPersistenceConstants.FIELD_PAYMENT_STATUS), criteria.getPaymentStatus()));
        }
        if (criteria.getPaymentMethod() != null) {
            predicates.add(cb.equal(root.get(OrderPersistenceConstants.FIELD_PAYMENT_METHOD), criteria.getPaymentMethod()));
        }
    }

    private static void applyDateRangeFilter(Root<OrderEntity> root, CriteriaBuilder cb, OrderSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getStartDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(OrderPersistenceConstants.FIELD_CREATED_AT), criteria.getStartDate()));
        }
        if (criteria.getEndDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(OrderPersistenceConstants.FIELD_CREATED_AT), criteria.getEndDate()));
        }
    }

    private static void applyGrandTotalFilter(Root<OrderEntity> root, CriteriaBuilder cb, OrderSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getMinGrandTotal() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(OrderPersistenceConstants.FIELD_GRAND_TOTAL), criteria.getMinGrandTotal()));
        }
        if (criteria.getMaxGrandTotal() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(OrderPersistenceConstants.FIELD_GRAND_TOTAL), criteria.getMaxGrandTotal()));
        }
    }

    private static void applyKeywordSearch(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb, OrderSearchCriteria criteria, List<Predicate> predicates) {
        if (criteria.getKeyword() == null || criteria.getKeyword().trim().isEmpty()) {
            return;
        }
        String pattern = "%" + criteria.getKeyword().trim().toLowerCase() + "%";

        Join<OrderEntity, OrderItemEntity> itemsJoin = root.join(OrderPersistenceConstants.FIELD_ITEMS, JoinType.LEFT);

        Predicate orderNumberMatch = cb.like(cb.lower(root.get(OrderPersistenceConstants.FIELD_ORDER_NUMBER)), pattern);
        Predicate shippingNameMatch = cb.like(cb.lower(root.get(OrderPersistenceConstants.FIELD_SHIPPING_FULL_NAME)), pattern);
        Predicate shippingCityMatch = cb.like(cb.lower(root.get(OrderPersistenceConstants.FIELD_SHIPPING_CITY)), pattern);
        Predicate bookTitleMatch = cb.like(cb.lower(itemsJoin.get(OrderPersistenceConstants.FIELD_BOOK_TITLE)), pattern);
        Predicate isbnMatch = cb.like(cb.lower(itemsJoin.get(OrderPersistenceConstants.FIELD_ISBN)), pattern);

        predicates.add(cb.or(orderNumberMatch, shippingNameMatch, shippingCityMatch, bookTitleMatch, isbnMatch));
        query.distinct(true);
    }
}
