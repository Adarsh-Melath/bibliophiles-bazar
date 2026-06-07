package com.adarsh.backend.shared.application.port;

import java.util.List;
import java.util.Optional;

import com.adarsh.backend.shared.domain.Address;

public interface AddressRepositoryPort {

    List<Address> findByUserId(Long id);

    Optional<Address> findByIdAndUserId(Long id, Long userId);

    Address save(Address address);

    void delete(Address address);
}
