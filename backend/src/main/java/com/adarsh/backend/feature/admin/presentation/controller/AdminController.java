package com.adarsh.backend.feature.admin.presentation.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.backend.feature.admin.application.usecase.ToggleBlockUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ToggleBlockUseCase toggleBlockUseCase;

    @PostMapping("/users/{id}/toggle-block")
    public ResponseEntity<String> toggleBlockUser(@PathVariable Long id){
        toggleBlockUseCase.execute(id);
        return ResponseEntity.ok("User block status toggled successfully");
    }
}
