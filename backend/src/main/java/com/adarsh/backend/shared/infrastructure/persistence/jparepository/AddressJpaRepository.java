package com.adarsh.backend.shared.infrastructure.persistence.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.backend.shared.infrastructure.persistence.entity.AddressEntity;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByUserId(Long userId);

    Optional<AddressEntity> findByIdAndUserId(Long id, Long userId);

    AddressEntity save(AddressEntity addressEntity);

    void delete(AddressEntity addressEntity);
}
