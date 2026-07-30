package com.adarsh.backend.feature.discount.application.usecase;

public interface VendorDeleteOfferUseCase {
    void execute(Long offerId, String vendorEmail);
}
