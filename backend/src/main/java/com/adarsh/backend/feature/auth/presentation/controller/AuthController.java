package com.adarsh.backend.feature.auth.presentation.controller;

import com.adarsh.backend.feature.auth.application.dto.command.*;
import com.adarsh.backend.feature.auth.application.dto.result.LoginResult;
import com.adarsh.backend.feature.auth.application.dto.result.RefreshAccessTokenResult;
import com.adarsh.backend.feature.auth.application.dto.result.VerifyResetOtpResult;
import com.adarsh.backend.feature.auth.application.usecase.*;
import com.adarsh.backend.feature.auth.presentation.constant.AuthControllerConstants;
import com.adarsh.backend.feature.auth.presentation.constant.AuthControllerLogMessageConstants;
import com.adarsh.backend.feature.auth.presentation.constant.AuthCookieConstants;
import com.adarsh.backend.feature.auth.presentation.constant.AuthSuccessMessageConstants;
import com.adarsh.backend.feature.auth.application.dto.command.VerifyOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.result.VerifyOtpResult;
import com.adarsh.backend.feature.auth.application.usecase.VerifyOtpUseCase;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final RegisterUserUseCase registerUserUseCase;
    private final VerifyOtpUseCase verifyOtpUseCase;
    private final LoginUseCase loginUseCase;
    private final ForgetPasswordUseCase forgetPasswordUseCase;
    private final VerifyResetOtpUseCase verifyResetOtpUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;
    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;

    @PostMapping(AuthControllerConstants.REGISTER_PATH)
    public ResponseEntity<String> register(@RequestBody RegisterUserCommand command) {
        logger.info(AuthControllerLogMessageConstants.REGISTER_REQUEST, command.email());
        registerUserUseCase.execute(command);
        logger.info(AuthControllerLogMessageConstants.REGISTER_SUCCESS, command.email());
        return ResponseEntity.status(HttpStatus.OK).body(AuthSuccessMessageConstants.REGISTRATION_SUCCESS);
    }

    @PostMapping(AuthControllerConstants.VERIFY_OTP_PATH)
    public ResponseEntity<VerifyOtpResult> verifyOtp(@RequestBody VerifyOtpCommand command) {
        logger.info(AuthControllerLogMessageConstants.VERIFY_OTP_REQUEST, command.email());
        VerifyOtpResult result = verifyOtpUseCase.execute(command);
        logger.info(AuthControllerLogMessageConstants.VERIFY_OTP_SUCCESS, command.email());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(AuthControllerConstants.LOGIN_PATH)
    public ResponseEntity<LoginResult> login(@RequestBody LoginCommand command, HttpServletResponse response) {
        logger.info(AuthControllerLogMessageConstants.LOGIN_REQUEST, command.email());
        LoginResult result = loginUseCase.execute(command);

        ResponseCookie cookie = ResponseCookie.from(AuthCookieConstants.REFRESH_TOKEN_COOKIE_NAME, result.refreshToken()).httpOnly(true).path(AuthCookieConstants.COOKIE_PATH).maxAge(AuthCookieConstants.REFRESH_TOKEN_COOKIE_MAX_AGE).sameSite(AuthCookieConstants.SAME_SITE_POLICY).build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        logger.info(AuthControllerLogMessageConstants.LOGIN_SUCCESS, command.email());
        return ResponseEntity.ok(result);
    }

    @PostMapping(AuthControllerConstants.REFRESH_PATH)
    public ResponseEntity<RefreshAccessTokenResult> refresh(@CookieValue(name = AuthCookieConstants.REFRESH_TOKEN_COOKIE_NAME, required = false) String refreshToken) {
        if (refreshToken == null) {
            logger.warn(AuthControllerLogMessageConstants.REFRESH_TOKEN_MISSING);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        logger.info(AuthControllerLogMessageConstants.REFRESH_TOKEN_REQUEST);
        RefreshAccessTokenResult result = refreshAccessTokenUseCase.execute(refreshToken);
        logger.info(AuthControllerLogMessageConstants.REFRESH_TOKEN_SUCCESS);
        return ResponseEntity.ok(result);
    }

    @PostMapping(AuthControllerConstants.FORGOT_PASSWORD_PATH)
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordCommand command) {
        logger.info(AuthControllerLogMessageConstants.FORGOT_PASSWORD_REQUEST, command.email());
        forgetPasswordUseCase.execute(command);
        logger.info(AuthControllerLogMessageConstants.FORGOT_PASSWORD_SUCCESS, command.email());
        return ResponseEntity.ok(AuthSuccessMessageConstants.PASSWORD_RESET_OTP_SENT);
    }

    @PostMapping(AuthControllerConstants.VERIFY_RESET_OTP_PATH)
    public ResponseEntity<VerifyResetOtpResult> verifyResetOtp(@RequestBody VerifyResetOtpCommand command) {
        logger.info(AuthControllerLogMessageConstants.VERIFY_RESET_OTP_REQUEST, command.email());
        VerifyResetOtpResult result = verifyResetOtpUseCase.execute(command);
        logger.info(AuthControllerLogMessageConstants.VERIFY_RESET_OTP_SUCCESS, command.email());
        return ResponseEntity.ok(result);
    }

    @PostMapping(AuthControllerConstants.RESET_PASSWORD_PATH)
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordCommand command) {
        logger.info(AuthControllerLogMessageConstants.RESET_PASSWORD_REQUEST, command.resetToken());
        resetPasswordUseCase.execute(command);
        logger.info(AuthControllerLogMessageConstants.RESET_PASSWORD_SUCCESS);
        return ResponseEntity.ok(AuthSuccessMessageConstants.PASSWORD_RESET_SUCCESS);
    }
}
