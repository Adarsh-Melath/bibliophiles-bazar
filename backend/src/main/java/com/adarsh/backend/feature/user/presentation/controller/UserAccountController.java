package com.adarsh.backend.feature.user.presentation.controller;

import com.adarsh.backend.feature.user.application.dto.command.ChangeEmailCommand;
import com.adarsh.backend.feature.user.application.dto.command.ChangePasswordCommand;
import com.adarsh.backend.feature.user.application.usecase.ChangePasswordUseCase;
import com.adarsh.backend.feature.user.application.usecase.ChangeEmailUseCase;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserAccountRoutes;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserApiRoutes;
import com.adarsh.backend.feature.user.presentation.constant.logconstant.UserControllerLogConstants;
import com.adarsh.backend.feature.user.presentation.constant.successmessageconstant.UserAccountControllerMessages;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserApiRoutes.BASE)
@RequiredArgsConstructor
public class UserAccountController {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    private static ChangePasswordUseCase changePasswordUseCase;
    private static ChangeEmailUseCase changeEmailUseCase;

    @PutMapping(UserAccountRoutes.PASSWORD)
    public ResponseEntity<String> changePassword(Authentication authentication, @RequestBody ChangePasswordCommand command) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.CHANGE_PASSWORD_REQUEST, email);
        changePasswordUseCase.execute(email, command);
        logger.info(UserControllerLogConstants.CHANGE_PASSWORD_SUCCESS, email);
        return ResponseEntity.ok(UserAccountControllerMessages.PASSWORD_CHANGED);
    }

    @PutMapping(UserAccountRoutes.EMAIL)
    public ResponseEntity<String> changeEmail(Authentication authentication, @RequestBody ChangeEmailCommand command) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.CHANGE_EMAIL_REQUEST, email);
        changeEmailUseCase.execute(email, command);
        logger.info(UserControllerLogConstants.CHANGE_EMAIL_SUCCESS, email);
        return ResponseEntity.ok(UserAccountControllerMessages.EMAIL_CHANGED);
    }
}
