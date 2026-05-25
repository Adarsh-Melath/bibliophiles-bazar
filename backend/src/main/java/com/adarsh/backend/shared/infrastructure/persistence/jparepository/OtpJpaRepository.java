package com.adarsh.backend.shared.infrastructure.persistence.jparepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.backend.shared.infrastructure.persistence.entity.OtpEntity;

public interface OtpJpaRepository extends JpaRepository<OtpEntity, Long> {
    OtpEntity save(OtpEntity otpToken);

    Optional<OtpEntity> getLatestOtpByEmail(String email);

    void deleteOtpByEmail(String email);
}
