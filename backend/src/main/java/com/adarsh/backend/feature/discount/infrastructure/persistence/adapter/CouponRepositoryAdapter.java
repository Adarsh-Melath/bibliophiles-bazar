package com.adarsh.backend.feature.discount.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.discount.application.port.CouponCommandRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.CouponSearchCriteria;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.feature.discount.infrastructure.persistence.constant.DiscountPersistenceConstants;
import com.adarsh.backend.feature.discount.infrastructure.persistence.entity.CouponEntity;
import com.adarsh.backend.feature.discount.infrastructure.persistence.jparepository.CouponJpaRepository;
import com.adarsh.backend.feature.discount.infrastructure.persistence.mapper.CouponPersistenceMapper;
import com.adarsh.backend.feature.discount.infrastructure.persistence.specification.CouponSpecification;
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
public class CouponRepositoryAdapter implements CouponCommandRepositoryPort, CouponQueryRepositoryPort {

    private final CouponJpaRepository couponJpaRepository;
    private final CouponPersistenceMapper couponPersistenceMapper;

    @Override
    public Coupon save(Coupon coupon) {
        CouponEntity entity = couponPersistenceMapper.toEntity(coupon);
        CouponEntity savedEntity = couponJpaRepository.save(entity);
        return couponPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteByCode(String code) {
        couponJpaRepository.deleteById(code);
    }

    @Override
    public Optional<Coupon> findByCode(String code) {
        return couponJpaRepository.findById(code).map(couponPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByCode(String code) {
        return couponJpaRepository.existsById(code);
    }

    @Override
    public PageResult<Coupon> search(PageQuery pageQuery, CouponSearchCriteria criteria) {
        Pageable pageable = PageRequest.of(pageQuery.page(), pageQuery.size(), Sort.by(DiscountPersistenceConstants.COUPON_FIELD_CODE).ascending());
        Specification<CouponEntity> spec = CouponSpecification.build(criteria);
        Page<CouponEntity> springPage = couponJpaRepository.findAll(spec, pageable);
        List<Coupon> domainCoupons = springPage.getContent().stream().map(couponPersistenceMapper::toDomain).toList();
        return new PageResult<>(domainCoupons, springPage.getNumber(), springPage.getSize(), (int) springPage.getTotalElements(), springPage.getTotalPages());
    }

    @Override
    public List<Coupon> findActiveCoupons() {
        LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
        List<CouponEntity> entities = couponJpaRepository.findByIsActiveTrueAndExpiryDateAfterOrExpiryDateIsNull(now);
        return entities.stream().map(couponPersistenceMapper::toDomain).toList();
    }
}
