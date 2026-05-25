package com.adarsh.backend.shared.infrastructure.persistence.adapters;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.adarsh.backend.shared.application.port.OtpTokenRepository;
import com.adarsh.backend.shared.domain.OtpToken;
import com.adarsh.backend.shared.infrastructure.persistence.entity.OtpEntity;
import com.adarsh.backend.shared.infrastructure.persistence.jparepository.OtpJpaRepository;
import com.adarsh.backend.shared.infrastructure.persistence.mapper.OtpPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OtpRepositoryAdapter implements OtpTokenRepository {

    private final OtpJpaRepository otpJpaRepository;
    private final OtpPersistenceMapper otpPersistenceMapper;

    @Override
    public OtpToken save(OtpToken otp) {
        OtpEntity savedEntity = otpJpaRepository.save(otpPersistenceMapper.toEntity(otp));
        return otpPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<OtpToken> getLatestOtpByEmail(String email) {
        return otpJpaRepository.getLatestOtpByEmail(email).map(otpPersistenceMapper::toDomain);
    }

    @Override
    public void deleteOtpByEmail(String email) {
        otpJpaRepository.deleteOtpByEmail(email);
    }
}
