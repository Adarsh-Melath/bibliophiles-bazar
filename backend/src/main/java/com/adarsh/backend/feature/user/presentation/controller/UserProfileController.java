package com.adarsh.backend.feature.user.presentation.controller;

import com.adarsh.backend.feature.user.application.dto.command.UpdateUserProfileCommand;
import com.adarsh.backend.feature.user.application.dto.result.GetUserProfileResult;
import com.adarsh.backend.feature.user.application.dto.result.UpdateUserProfileResult;
import com.adarsh.backend.feature.user.application.usecase.GetUserProfileUseCase;
import com.adarsh.backend.feature.user.application.usecase.UpdateUserProfileUseCase;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserApiRoutes;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserProfileRoutes;
import com.adarsh.backend.feature.user.presentation.constant.logconstant.UserControllerLogConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserApiRoutes.BASE)
@RequiredArgsConstructor
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private static GetUserProfileUseCase getUserProfileUseCase;
    private static UpdateUserProfileUseCase updateUserProfileUseCase;

    @GetMapping(UserProfileRoutes.PROFILE)
    public ResponseEntity<GetUserProfileResult> getProfile(Authentication authentication) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.GET_PROFILE_REQUEST, email);
        GetUserProfileResult result = getUserProfileUseCase.execute(email);
        return ResponseEntity.ok(result);
    }

    @PostMapping(UserProfileRoutes.PROFILE)
    public ResponseEntity<UpdateUserProfileResult> updateProfile(Authentication authentication, @RequestBody UpdateUserProfileCommand command) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.UPDATE_PROFILE_REQUEST, email);
        UpdateUserProfileResult result = updateUserProfileUseCase.execute(email, command);
        logger.info(UserControllerLogConstants.UPDATE_PROFILE_SUCCESS, email);
        return ResponseEntity.ok(result);
    }
}
