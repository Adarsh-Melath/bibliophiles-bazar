package com.adarsh.backend.feature.discount.application.port;

import com.adarsh.backend.feature.discount.domain.model.Coupon;

public interface CouponCommandRepositoryPort {

    Coupon save(Coupon coupon);

    void deleteByCode(String code);
}
