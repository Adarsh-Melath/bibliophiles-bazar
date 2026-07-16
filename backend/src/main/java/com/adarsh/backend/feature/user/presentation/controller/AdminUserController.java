package com.adarsh.backend.feature.user.presentation.controller;

import com.adarsh.backend.feature.user.application.dto.result.GetUsersResult;
import com.adarsh.backend.feature.user.application.usecase.DeleteUserUseCase;
import com.adarsh.backend.feature.user.application.usecase.GetUsersUseCase;
import com.adarsh.backend.feature.user.application.usecase.ToggleBlockUseCase;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.AdminUserRoutes;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserApiRoutes;
import com.adarsh.backend.feature.user.presentation.constant.logconstant.UserControllerLogConstants;
import com.adarsh.backend.feature.user.presentation.constant.successmessageconstant.AdminUserControllerMessages;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserApiRoutes.BASE)
public class AdminUserController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    private final GetUsersUseCase getUsersUseCase;
    private final ToggleBlockUseCase toggleBlockUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping(AdminUserRoutes.USERS)
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResult<GetUsersResult>> getUsers(@RequestParam(required = false) String search, @RequestParam(required = false) Role role, @RequestParam(required = false) Boolean enabled, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        logger.info(UserControllerLogConstants.GET_USERS_REQUEST, page, size);
        PageResult<GetUsersResult> result = getUsersUseCase.execute(search, role, enabled, page, size);
        return ResponseEntity.ok(result);
    }

    @PutMapping(AdminUserRoutes.USER_BLOCK_STATUS)
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> toggleBlockUser(@PathVariable Long id) {
        logger.info(UserControllerLogConstants.TOGGLE_BLOCK_REQUEST, id);
        toggleBlockUseCase.execute(id);
        logger.info(UserControllerLogConstants.TOGGLE_BLOCK_SUCCESS, id);
        return ResponseEntity.ok(AdminUserControllerMessages.USER_BLOCK_STATUS_UPDATED);
    }

    @DeleteMapping(AdminUserRoutes.USER_BY_ID)
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        logger.info(UserControllerLogConstants.DELETE_USER_REQUEST, id);
        deleteUserUseCase.execute(id);
        logger.info(UserControllerLogConstants.DELETE_USER_SUCCESS, id);
        return ResponseEntity.ok(AdminUserControllerMessages.USER_DELETED);
    }
}
