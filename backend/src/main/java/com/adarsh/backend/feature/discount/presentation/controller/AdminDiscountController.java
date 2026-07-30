package com.adarsh.backend.feature.discount.presentation.controller;

import com.adarsh.backend.feature.discount.application.dto.command.CreateCategoryOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.command.CreateCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.command.CreateProductOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.command.UpdateCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.command.UpdateOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateCouponResult;
import com.adarsh.backend.feature.discount.application.dto.result.CreateOfferResult;
import com.adarsh.backend.feature.discount.application.dto.result.GetCouponByCodeResult;
import com.adarsh.backend.feature.discount.application.dto.result.SearchCouponsResult;
import com.adarsh.backend.feature.discount.application.dto.result.SearchOffersResult;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateCouponResult;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateOfferResult;
import com.adarsh.backend.feature.discount.application.usecase.AdminDeactivateOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.AdminDeleteOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.AdminUpdateOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.CreateCategoryOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.CreateCouponUseCase;
import com.adarsh.backend.feature.discount.application.usecase.CreateProductOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.DeleteCouponUseCase;
import com.adarsh.backend.feature.discount.application.usecase.GetCouponByCodeUseCase;
import com.adarsh.backend.feature.discount.application.usecase.SearchCouponsUseCase;
import com.adarsh.backend.feature.discount.application.usecase.SearchOffersUseCase;
import com.adarsh.backend.feature.discount.application.usecase.UpdateCouponUseCase;
import com.adarsh.backend.feature.discount.domain.model.DiscountType;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.feature.discount.presentation.constant.apiconstant.DiscountControllerApiConstants;
import com.adarsh.backend.feature.discount.presentation.constant.logconstant.DiscountControllerLogConstants;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminDiscountController {

    private static final Logger logger = LoggerFactory.getLogger(AdminDiscountController.class);

    private final CreateCouponUseCase createCouponUseCase;
    private final UpdateCouponUseCase updateCouponUseCase;
    private final DeleteCouponUseCase deleteCouponUseCase;
    private final GetCouponByCodeUseCase getCouponByCodeUseCase;
    private final SearchCouponsUseCase searchCouponsUseCase;

    private final CreateCategoryOfferUseCase createCategoryOfferUseCase;
    private final CreateProductOfferUseCase createProductOfferUseCase;
    private final AdminUpdateOfferUseCase adminUpdateOfferUseCase;
    private final AdminDeactivateOfferUseCase adminDeactivateOfferUseCase;
    private final AdminDeleteOfferUseCase adminDeleteOfferUseCase;
    private final SearchOffersUseCase searchOffersUseCase;

    // --- COUPON ENDPOINTS ---

    @PostMapping(DiscountControllerApiConstants.ADMIN_COUPONS_BASE_PATH)
    public ResponseEntity<CreateCouponResult> createCoupon(@RequestBody CreateCouponCommand command) {
        logger.info(DiscountControllerLogConstants.ADMIN_CREATE_COUPON_REQUEST, command.code());

        CreateCouponResult result = createCouponUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(DiscountControllerApiConstants.ADMIN_COUPONS_BASE_PATH + "/{code}")
    public ResponseEntity<UpdateCouponResult> updateCoupon(@PathVariable String code, @RequestBody UpdateCouponCommand command) {
        logger.info(DiscountControllerLogConstants.ADMIN_UPDATE_COUPON_REQUEST, code);

        UpdateCouponResult result = updateCouponUseCase.execute(code, command);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(DiscountControllerApiConstants.ADMIN_COUPONS_BASE_PATH + "/{code}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable String code) {
        logger.info(DiscountControllerLogConstants.ADMIN_DELETE_COUPON_REQUEST, code);

        deleteCouponUseCase.execute(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(DiscountControllerApiConstants.ADMIN_COUPONS_BASE_PATH + "/{code}")
    public ResponseEntity<GetCouponByCodeResult> getCouponByCode(@PathVariable String code) {
        logger.info(DiscountControllerLogConstants.ADMIN_GET_COUPON_REQUEST, code);

        GetCouponByCodeResult result = getCouponByCodeUseCase.execute(code);
        return ResponseEntity.ok(result);
    }

    @GetMapping(DiscountControllerApiConstants.ADMIN_COUPONS_BASE_PATH)
    public ResponseEntity<PageResult<SearchCouponsResult>> searchCoupons(@RequestParam(required = false) String keyword, @RequestParam(required = false) Boolean isActive, @RequestParam(required = false) DiscountType discountType, @RequestParam(defaultValue = DiscountControllerApiConstants.DEFAULT_PAGE) int page, @RequestParam(defaultValue = DiscountControllerApiConstants.DEFAULT_SIZE) int size) {
        logger.info(DiscountControllerLogConstants.ADMIN_SEARCH_COUPONS_REQUEST, keyword, isActive, page, size);

        PageResult<SearchCouponsResult> result = searchCouponsUseCase.execute(keyword, isActive, discountType, page, size);
        return ResponseEntity.ok(result);
    }

    // --- OFFER ENDPOINTS ---

    @PostMapping(DiscountControllerApiConstants.ADMIN_OFFERS_BASE_PATH + "/category")
    public ResponseEntity<CreateOfferResult> createCategoryOffer(@RequestBody CreateCategoryOfferCommand command) {
        logger.info(DiscountControllerLogConstants.ADMIN_CREATE_CATEGORY_OFFER_REQUEST, command.categoryId());

        CreateOfferResult result = createCategoryOfferUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping(DiscountControllerApiConstants.ADMIN_OFFERS_BASE_PATH + "/product")
    public ResponseEntity<CreateOfferResult> createPlatformProductOffer(@RequestBody CreateProductOfferCommand command) {
        logger.info(DiscountControllerLogConstants.ADMIN_CREATE_PRODUCT_OFFER_REQUEST, command.bookId());

        CreateOfferResult result = createProductOfferUseCase.execute(null, command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(DiscountControllerApiConstants.ADMIN_OFFERS_BASE_PATH + "/{id}")
    public ResponseEntity<UpdateOfferResult> updateOffer(@PathVariable Long id, @RequestBody UpdateOfferCommand command) {
        logger.info(DiscountControllerLogConstants.ADMIN_UPDATE_OFFER_REQUEST, id);

        UpdateOfferResult result = adminUpdateOfferUseCase.execute(id, command);
        return ResponseEntity.ok(result);
    }

    @PatchMapping(DiscountControllerApiConstants.ADMIN_OFFERS_BASE_PATH + "/{id}/deactivate")
    public ResponseEntity<Void> deactivateOffer(@PathVariable Long id) {
        logger.info(DiscountControllerLogConstants.ADMIN_DEACTIVATE_OFFER_REQUEST, id);

        adminDeactivateOfferUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(DiscountControllerApiConstants.ADMIN_OFFERS_BASE_PATH + "/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        logger.info(DiscountControllerLogConstants.ADMIN_DELETE_OFFER_REQUEST, id);

        adminDeleteOfferUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(DiscountControllerApiConstants.ADMIN_OFFERS_BASE_PATH)
    public ResponseEntity<PageResult<SearchOffersResult>> searchOffers(@RequestParam(required = false) String keyword, @RequestParam(required = false) OfferType offerType, @RequestParam(required = false) Long targetId, @RequestParam(required = false) Long publisherId, @RequestParam(required = false) Boolean isActive, @RequestParam(defaultValue = DiscountControllerApiConstants.DEFAULT_PAGE) int page, @RequestParam(defaultValue = DiscountControllerApiConstants.DEFAULT_SIZE) int size) {
        logger.info(DiscountControllerLogConstants.ADMIN_SEARCH_OFFERS_REQUEST, keyword, offerType, page, size);

        PageResult<SearchOffersResult> result = searchOffersUseCase.execute(keyword, offerType, targetId, publisherId, isActive, page, size);
        return ResponseEntity.ok(result);
    }
}
