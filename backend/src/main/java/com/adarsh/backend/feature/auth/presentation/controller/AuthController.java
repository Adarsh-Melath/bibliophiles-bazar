package com.adarsh.backend.feature.auth.presentation.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.backend.feature.auth.application.dto.CreateUserCommand;
import com.adarsh.backend.feature.auth.application.dto.ForgetPasswordCommand;
import com.adarsh.backend.feature.auth.application.dto.LoginCommand;
import com.adarsh.backend.feature.auth.application.dto.LoginResult;
import com.adarsh.backend.feature.auth.application.dto.ResetPasswordCommand;
import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpResult;
import com.adarsh.backend.feature.auth.application.usecase.CreateUserUseCase;
import com.adarsh.backend.feature.auth.application.usecase.ForgetPasswordUseCase;
import com.adarsh.backend.feature.auth.application.usecase.LoginUseCase;
import com.adarsh.backend.feature.auth.application.usecase.ResetPasswordUseCase;
import com.adarsh.backend.feature.auth.application.usecase.VerifyResetOtpUseCase;
import com.adarsh.backend.shared.application.dto.VerifyOtpCommand;
import com.adarsh.backend.shared.application.dto.VerifyOtpResult;
import com.adarsh.backend.shared.application.usecase.VerifyOtpUseCase;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserUseCase createUserUseCase;
    private final VerifyOtpUseCase verifyOtpUseCase;
    private final LoginUseCase loginUsecase;
    private final ForgetPasswordUseCase forgetPasswordUseCase;
    private final VerifyResetOtpUseCase verifyResetOtpUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateUserCommand command) {
        createUserUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.OK).body("Registration successful. Check your email for OTP.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<VerifyOtpResult> verifyOtp(@RequestBody VerifyOtpCommand command) {
        return ResponseEntity.status(HttpStatus.OK).body(verifyOtpUseCase.execute(command));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody LoginCommand command, HttpServletResponse response) {
        LoginResult result = loginUsecase.execute(command);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", result.getRefreshToken()).httpOnly(true)
                .path("/api").maxAge(7 * 24 * 60 * (long) 60).sameSite("Lax").build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        result.setRefreshToken(null);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(ForgetPasswordCommand command) {
        forgetPasswordUseCase.execute(command);
        return ResponseEntity.ok("OTP sent to your email");
    }

    @PostMapping("/verify-reset-otp")
    public ResponseEntity<VerifyResetOtpResult> verifyResetOtp(VerifyResetOtpCommand command) {
        return ResponseEntity.ok(verifyResetOtpUseCase.execute(command));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(ResetPasswordCommand command) {
        resetPasswordUseCase.execute(command);
        return ResponseEntity.ok("Password reset successfully");
    }
}
