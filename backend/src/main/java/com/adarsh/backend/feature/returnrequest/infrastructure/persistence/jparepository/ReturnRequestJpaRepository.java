package com.adarsh.backend.feature.returnrequest.infrastructure.persistence.jparepository;

import com.adarsh.backend.feature.returnrequest.infrastructure.persistence.entity.ReturnRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRequestJpaRepository extends JpaRepository<ReturnRequestEntity, Long> {
}
