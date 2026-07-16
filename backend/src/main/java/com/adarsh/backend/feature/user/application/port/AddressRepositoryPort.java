package com.adarsh.backend.feature.user.application.port;

import com.adarsh.backend.feature.user.domain.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepositoryPort {

    List<Address> findByUserId(Long id);

    Optional<Address> findByIdAndUserId(Long id, Long userId);

    Address save(Address address);

    void delete(Address address);
}
