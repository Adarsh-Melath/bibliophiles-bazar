package com.adarsh.backend.feature.discount.application.port;

import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.List;
import java.util.Optional;

public interface CouponQueryRepositoryPort {

    Optional<Coupon> findByCode(String code);

    boolean existsByCode(String code);

    PageResult<Coupon> search(PageQuery pageQuery, CouponSearchCriteria criteria);

    List<Coupon> findActiveCoupons();
}
