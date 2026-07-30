package com.adarsh.backend.feature.discount.presentation.controller;

import com.adarsh.backend.feature.discount.application.dto.command.ApplyCouponCommand;
import com.adarsh.backend.feature.discount.application.dto.result.ApplyCouponResult;
import com.adarsh.backend.feature.discount.application.dto.result.GetActiveCouponsResult;
import com.adarsh.backend.feature.discount.application.dto.result.GetActiveOfferForBookResult;
import com.adarsh.backend.feature.discount.application.usecase.ApplyCouponUseCase;
import com.adarsh.backend.feature.discount.application.usecase.GetActiveCouponsUseCase;
import com.adarsh.backend.feature.discount.application.usecase.GetActiveOfferForBookUseCase;
import com.adarsh.backend.feature.discount.application.usecase.RemoveCouponUseCase;
import com.adarsh.backend.feature.discount.application.usecase.ValidateCouponUseCase;
import com.adarsh.backend.feature.discount.presentation.constant.apiconstant.DiscountControllerApiConstants;
import com.adarsh.backend.feature.discount.presentation.constant.logconstant.DiscountControllerLogConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DiscountControllerApiConstants.CUSTOMER_DISCOUNTS_BASE_PATH)
@RequiredArgsConstructor
public class CustomerDiscountController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDiscountController.class);

    private final ApplyCouponUseCase applyCouponUseCase;
    private final RemoveCouponUseCase removeCouponUseCase;
    private final ValidateCouponUseCase validateCouponUseCase;
    private final GetActiveCouponsUseCase getActiveCouponsUseCase;
    private final GetActiveOfferForBookUseCase getActiveOfferForBookUseCase;

    @PostMapping("/coupons/apply")
    public ResponseEntity<ApplyCouponResult> applyCoupon(Authentication authentication, @RequestBody ApplyCouponCommand command) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.CUSTOMER_APPLY_COUPON_REQUEST, command.couponCode(), email);

        ApplyCouponResult result = applyCouponUseCase.execute(email, command);
        logger.info(DiscountControllerLogConstants.CUSTOMER_APPLY_COUPON_SUCCESS, command.couponCode());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/coupons/remove")
    public ResponseEntity<ApplyCouponResult> removeCoupon(Authentication authentication) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.CUSTOMER_REMOVE_COUPON_REQUEST, email);

        ApplyCouponResult result = removeCouponUseCase.execute(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/coupons/validate")
    public ResponseEntity<ApplyCouponResult> validateCoupon(@RequestParam String code, @RequestParam Double subtotal) {
        logger.info(DiscountControllerLogConstants.CUSTOMER_VALIDATE_COUPON_REQUEST, code, subtotal);

        ApplyCouponResult result = validateCouponUseCase.execute(code, subtotal);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/coupons/active")
    public ResponseEntity<List<GetActiveCouponsResult>> getActiveCoupons() {
        logger.info(DiscountControllerLogConstants.CUSTOMER_GET_ACTIVE_COUPONS_REQUEST);

        List<GetActiveCouponsResult> result = getActiveCouponsUseCase.execute();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/offers/books/{bookId}")
    public ResponseEntity<GetActiveOfferForBookResult> getActiveOfferForBook(@PathVariable Long bookId) {
        logger.info(DiscountControllerLogConstants.CUSTOMER_GET_BOOK_OFFER_REQUEST, bookId);

        GetActiveOfferForBookResult result = getActiveOfferForBookUseCase.execute(bookId);
        return ResponseEntity.ok(result);
    }
}
