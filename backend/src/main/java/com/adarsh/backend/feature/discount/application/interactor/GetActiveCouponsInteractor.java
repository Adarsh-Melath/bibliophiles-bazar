package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.result.GetActiveCouponsResult;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.usecase.GetActiveCouponsUseCase;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetActiveCouponsInteractor implements GetActiveCouponsUseCase {

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;

    @Override
    public List<GetActiveCouponsResult> execute() {
        List<Coupon> activeCoupons = couponQueryRepositoryPort.findActiveCoupons();
        return activeCoupons.stream().map(GetActiveCouponsResult::fromDomain).toList();
    }
}
