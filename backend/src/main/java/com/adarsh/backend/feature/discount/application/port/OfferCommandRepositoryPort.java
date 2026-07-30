package com.adarsh.backend.feature.discount.application.port;

import com.adarsh.backend.feature.discount.domain.model.Offer;

public interface OfferCommandRepositoryPort {

    Offer save(Offer offer);

    void deleteById(Long id);
}
