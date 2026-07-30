package com.adarsh.backend.feature.discount.application.interactor;

import com.adarsh.backend.feature.discount.application.dto.result.SearchCouponsResult;
import com.adarsh.backend.feature.discount.application.interactor.constant.DiscountInteractorLogConstants;
import com.adarsh.backend.feature.discount.application.port.CouponQueryRepositoryPort;
import com.adarsh.backend.feature.discount.application.port.CouponSearchCriteria;
import com.adarsh.backend.feature.discount.application.usecase.SearchCouponsUseCase;
import com.adarsh.backend.feature.discount.domain.model.Coupon;
import com.adarsh.backend.feature.discount.domain.model.DiscountType;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchCouponsInteractor implements SearchCouponsUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SearchCouponsInteractor.class);

    private final CouponQueryRepositoryPort couponQueryRepositoryPort;

    @Override
    public PageResult<SearchCouponsResult> execute(String keyword, Boolean isActive, DiscountType discountType, int page, int size) {
        logger.info(DiscountInteractorLogConstants.SEARCH_COUPONS_REQUEST, keyword, isActive, page, size);

        PageQuery pageQuery = new PageQuery(page, size);
        CouponSearchCriteria criteria = new CouponSearchCriteria.Builder()
                .keyword(keyword)
                .isActive(isActive)
                .discountType(discountType)
                .build();

        PageResult<Coupon> domainPage = couponQueryRepositoryPort.search(pageQuery, criteria);
        return domainPage.map(SearchCouponsResult::fromDomain);
    }
}
