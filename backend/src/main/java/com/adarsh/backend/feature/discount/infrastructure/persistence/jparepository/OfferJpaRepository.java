package com.adarsh.backend.feature.discount.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferEntity, Long>, JpaSpecificationExecutor<OfferEntity> {

    Optional<OfferEntity> findByIdAndPublisherId(Long id, Long publisherId);

    List<OfferEntity> findByTargetIdAndOfferTypeAndIsActiveTrue(Long targetId, OfferType offerType);

    @Query("SELECT o FROM OfferEntity o WHERE o.isActive = true AND " +
            "((o.offerType = 'PRODUCT' AND o.targetId = :bookId) OR (o.offerType = 'CATEGORY' AND o.targetId = :categoryId)) AND " +
            "(o.startDate IS NULL OR o.startDate <= :now) AND (o.endDate IS NULL OR o.endDate >= :now)")
    List<OfferEntity> findActiveOffersForBookAndCategory(@Param("bookId") Long bookId, @Param("categoryId") Long categoryId, @Param("now") LocalDateTime now);
}
