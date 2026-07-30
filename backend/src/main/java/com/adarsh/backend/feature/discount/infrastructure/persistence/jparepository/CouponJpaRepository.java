package com.adarsh.backend.feature.discount.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CouponJpaRepository extends JpaRepository<CouponEntity, String>, JpaSpecificationExecutor<CouponEntity> {
    List<CouponEntity> findByIsActiveTrueAndExpiryDateAfterOrExpiryDateIsNull(LocalDateTime now);
}
