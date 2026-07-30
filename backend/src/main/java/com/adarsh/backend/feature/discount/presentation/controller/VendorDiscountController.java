package com.adarsh.backend.feature.discount.presentation.controller;

import com.adarsh.backend.feature.discount.application.dto.command.CreateProductOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.command.UpdateOfferCommand;
import com.adarsh.backend.feature.discount.application.dto.result.CreateOfferResult;
import com.adarsh.backend.feature.discount.application.dto.result.SearchOffersResult;
import com.adarsh.backend.feature.discount.application.dto.result.UpdateOfferResult;
import com.adarsh.backend.feature.discount.application.usecase.CreateProductOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.SearchOffersUseCase;
import com.adarsh.backend.feature.discount.application.usecase.VendorDeactivateOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.VendorDeleteOfferUseCase;
import com.adarsh.backend.feature.discount.application.usecase.VendorUpdateOfferUseCase;
import com.adarsh.backend.feature.discount.domain.model.OfferType;
import com.adarsh.backend.feature.discount.presentation.constant.apiconstant.DiscountControllerApiConstants;
import com.adarsh.backend.feature.discount.presentation.constant.logconstant.DiscountControllerLogConstants;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
@RequestMapping(DiscountControllerApiConstants.VENDOR_OFFERS_BASE_PATH)
@RequiredArgsConstructor
public class VendorDiscountController {

    private static final Logger logger = LoggerFactory.getLogger(VendorDiscountController.class);

    private final UserQueryRepository userQueryRepository;
    private final CreateProductOfferUseCase createProductOfferUseCase;
    private final VendorUpdateOfferUseCase vendorUpdateOfferUseCase;
    private final VendorDeactivateOfferUseCase vendorDeactivateOfferUseCase;
    private final VendorDeleteOfferUseCase vendorDeleteOfferUseCase;
    private final SearchOffersUseCase searchOffersUseCase;

    @PostMapping
    public ResponseEntity<CreateOfferResult> createProductOffer(Authentication authentication, @RequestBody CreateProductOfferCommand command) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.VENDOR_CREATE_OFFER_REQUEST, email, command.bookId());

        CreateOfferResult result = createProductOfferUseCase.execute(email, command);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateOfferResult> updateOffer(Authentication authentication, @PathVariable Long id, @RequestBody UpdateOfferCommand command) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.VENDOR_UPDATE_OFFER_REQUEST, id, email);

        UpdateOfferResult result = vendorUpdateOfferUseCase.execute(id, email, command);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateOffer(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.VENDOR_DEACTIVATE_OFFER_REQUEST, id, email);

        vendorDeactivateOfferUseCase.execute(id, email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.VENDOR_DELETE_OFFER_REQUEST, id, email);

        vendorDeleteOfferUseCase.execute(id, email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResult<SearchOffersResult>> searchVendorOffers(Authentication authentication, @RequestParam(required = false) String keyword, @RequestParam(required = false) Boolean isActive, @RequestParam(defaultValue = DiscountControllerApiConstants.DEFAULT_PAGE) int page, @RequestParam(defaultValue = DiscountControllerApiConstants.DEFAULT_SIZE) int size) {
        String email = authentication.getName();
        logger.info(DiscountControllerLogConstants.VENDOR_SEARCH_OFFERS_REQUEST, email, page, size);

        User publisher = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        PageResult<SearchOffersResult> result = searchOffersUseCase.execute(keyword, OfferType.PRODUCT, null, publisher.getId(), isActive, page, size);
        return ResponseEntity.ok(result);
    }
}
