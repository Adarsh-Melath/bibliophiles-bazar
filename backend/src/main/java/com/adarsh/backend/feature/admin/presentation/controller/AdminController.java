package com.adarsh.backend.feature.admin.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.backend.feature.admin.application.dto.GetUserResult;
import com.adarsh.backend.feature.admin.application.usecase.GetUserUseCase;
import com.adarsh.backend.feature.admin.application.usecase.ToggleBlockUseCase;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ToggleBlockUseCase toggleBlockUseCase;
    private final GetUserUseCase getUserUseCase;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResult<GetUserResult>> getUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResult<GetUserResult> result = getUserUseCase.execute(search, role, enabled, page, size);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/users/{id}/toggle-block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> toggleBlockUser(@PathVariable Long id) {
        toggleBlockUseCase.execute(id);
        return ResponseEntity.ok("User block status toggled successfully");
    }
}
