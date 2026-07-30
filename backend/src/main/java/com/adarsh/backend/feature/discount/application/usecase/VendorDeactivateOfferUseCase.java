package com.adarsh.backend.feature.discount.application.usecase;

public interface VendorDeactivateOfferUseCase {
    void execute(Long offerId, String vendorEmail);
}
