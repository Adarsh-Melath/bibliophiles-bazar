package com.adarsh.backend.feature.user.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.user.infrastructure.persistence.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByUserId(Long userId);

    Optional<AddressEntity> findByIdAndUserId(Long id, Long userId);

    AddressEntity save(AddressEntity addressEntity);

    void delete(AddressEntity addressEntity);
}
