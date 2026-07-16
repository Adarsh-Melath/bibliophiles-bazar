package com.adarsh.backend.feature.book.infrastructure.persistence.jpaRepository;

import com.adarsh.backend.feature.book.infrastructure.persistence.entity.BookEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookJpaSpecification {
    public static Specification<BookEntity> keywordContains(String keyword) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (keyword != null && !keyword.isBlank()) {
                Predicate titleMatch = criteriaBuilder.like(root.get("title"), "%" + keyword + "%");
                Predicate authorMatch = criteriaBuilder.like(root.get("author"), "%" + keyword + "%");

                predicatesList.add(titleMatch);
                predicatesList.add(authorMatch);
            }

            // if list is empty,return true(Match all)
            if (predicatesList.isEmpty()) return criteriaBuilder.conjunction();

            //return the OR combination of all predicates
            return criteriaBuilder.or(predicatesList.toArray(new Predicate[0]));

        };
    }

    public static Specification<BookEntity> categoryNameEquals(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category != null && !category.isBlank()) {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get("category")), category);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<BookEntity> priceGreaterThanOrEqual(Double minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<BookEntity> priceLessThaOrEqual(Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
            return criteriaBuilder.conjunction();
        };
    }
}

