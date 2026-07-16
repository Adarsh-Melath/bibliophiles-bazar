package com.adarsh.backend.feature.user.infrastructure.persistence.adapter;

import com.adarsh.backend.feature.user.application.port.AddressRepositoryPort;
import com.adarsh.backend.feature.user.domain.model.Address;
import com.adarsh.backend.feature.user.infrastructure.persistence.entity.AddressEntity;
import com.adarsh.backend.feature.user.infrastructure.persistence.jparepository.AddressJpaRepository;
import com.adarsh.backend.feature.user.infrastructure.persistence.mapper.AddressPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryAdapter implements AddressRepositoryPort {
    private final AddressJpaRepository addressJpaRepository;
    private final AddressPersistenceMapper addressPersistenceMapper;

    @Override
    public List<Address> findByUserId(Long id) {
        return addressJpaRepository.findByUserId(id).stream().map(addressPersistenceMapper::toDomain).toList();
    }

    @Override
    public Optional<Address> findByIdAndUserId(Long id, Long userId) {
        return addressJpaRepository.findByIdAndUserId(id, userId).map(addressPersistenceMapper::toDomain);
    }

    @Override
    public Address save(Address address) {
        AddressEntity addressEntity = addressPersistenceMapper.toEntity(address);
        return addressPersistenceMapper.toDomain(addressJpaRepository.save(addressEntity));
    }

    @Override
    public void delete(Address address) {
        AddressEntity addressEntity = addressPersistenceMapper.toEntity(address);
        addressJpaRepository.delete(addressEntity);
    }
}
