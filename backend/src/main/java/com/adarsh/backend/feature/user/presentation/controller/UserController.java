package com.adarsh.backend.feature.user.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adarsh.backend.feature.user.application.dto.ChangePasswordCommand;
import com.adarsh.backend.feature.user.application.dto.GetUserProfileResult;
import com.adarsh.backend.feature.user.application.usecase.ChangePasswordUseCase;
import com.adarsh.backend.feature.user.application.usecase.GetUserProfileUseCase;
import com.adarsh.backend.feature.user.application.dto.UpdateUserProfileCommand;
import com.adarsh.backend.feature.user.application.dto.UpdateUserProfileResult;
import com.adarsh.backend.feature.user.application.usecase.UpdateUserProfileUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final GetUserProfileUseCase getUserProfileUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;

    @GetMapping("/profile")
    public ResponseEntity<GetUserProfileResult> getProfile(@AuthenticationPrincipal String email) {
        GetUserProfileResult result = getUserProfileUseCase.execute(email);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update-profile")
    public ResponseEntity<UpdateUserProfileResult> updateProfile(@AuthenticationPrincipal String email,  @RequestBody UpdateUserProfileCommand command){
        UpdateUserProfileResult result = updateUserProfileUseCase.execute(email, command);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal String email, @RequestBody ChangePasswordCommand command) {
        changePasswordUseCase.execute(email, command);
        return ResponseEntity.ok("Password Changed Successfully");
    }
}
