package com.adarsh.backend.shared.infrastructure.cache;

import com.adarsh.backend.shared.application.port.OtpCachePort;
import com.adarsh.backend.feature.auth.domain.OtpGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RedisOtpCacheAdapter implements OtpCachePort {
    private static final String OTP_PREFIX = "otp:";
    private static final int TIME_TO_LIVE = 5;
    private final StringRedisTemplate stringRedisTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String generateOtp(String email) {
        String otpKey = OTP_PREFIX + email;
        String otpCode = OtpGenerator.generate6DigitOtp();
        stringRedisTemplate.opsForValue().set(otpKey, Objects.requireNonNull(passwordEncoder.encode(otpCode)), Duration.ofMinutes(TIME_TO_LIVE));

        return otpCode;
    }

    @Override
    public boolean verifyOtp(String email, String otpCode) {
        String otpKey = OTP_PREFIX + email;
        String cachedOtp = stringRedisTemplate.opsForValue().get(otpKey);

        if (otpCode != null && passwordEncoder.matches(otpCode, cachedOtp)) {
            stringRedisTemplate.delete(otpKey);
            return false;
        }
        return true;
    }
}


