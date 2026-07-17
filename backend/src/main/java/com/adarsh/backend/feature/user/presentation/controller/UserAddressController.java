package com.adarsh.backend.feature.user.presentation.controller;

import com.adarsh.backend.feature.user.application.dto.command.AddAddressCommand;
import com.adarsh.backend.feature.user.application.dto.command.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.dto.result.AddAddressResult;
import com.adarsh.backend.feature.user.application.dto.result.GetAddressResult;
import com.adarsh.backend.feature.user.application.dto.result.UpdateAddressResult;
import com.adarsh.backend.feature.user.application.usecase.AddAddressUseCase;
import com.adarsh.backend.feature.user.application.usecase.DeleteAddressUseCase;
import com.adarsh.backend.feature.user.application.usecase.GetAddressesUseCase;
import com.adarsh.backend.feature.user.application.usecase.UpdateAddressUseCase;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserAddressRoutes;
import com.adarsh.backend.feature.user.presentation.constant.apiconstant.UserApiRoutes;
import com.adarsh.backend.feature.user.presentation.constant.logconstant.UserControllerLogConstants;
import com.adarsh.backend.feature.user.presentation.constant.successmessageconstant.UserAddressControllerMessages;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserApiRoutes.BASE)
@RequiredArgsConstructor
public class UserAddressController {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    private final GetAddressesUseCase getAddressesUseCase;
    private final AddAddressUseCase addAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    @GetMapping(UserAddressRoutes.ADDRESSES)
    public ResponseEntity<List<GetAddressResult>> getUserAddresses(Authentication authentication) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.GET_ADDRESSES_REQUEST, email);
        List<GetAddressResult> result = getAddressesUseCase.execute(email);
        return ResponseEntity.ok(result);
    }

    @PostMapping(UserAddressRoutes.ADDRESSES)
    public ResponseEntity<AddAddressResult> addUserAddress(Authentication authentication, @RequestBody AddAddressCommand command) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.ADD_ADDRESS_REQUEST, email);
        AddAddressResult result = addAddressUseCase.execute(email, command);
        logger.info(UserControllerLogConstants.ADD_ADDRESS_SUCCESS, email);
        return ResponseEntity.ok(result);
    }

    @PutMapping(UserAddressRoutes.ADDRESS_BY_ID)
    public ResponseEntity<UpdateAddressResult> updateUserAddress(Authentication authentication, @PathVariable Long id, @RequestBody UpdateAddressCommand command) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.UPDATE_ADDRESS_REQUEST, id, email);
        UpdateAddressResult result = updateAddressUseCase.execute(email, id, command);
        logger.info(UserControllerLogConstants.UPDATE_ADDRESS_SUCCESS, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(UserAddressRoutes.ADDRESS_BY_ID)
    public ResponseEntity<String> deleteUserAddress(Authentication authentication, @PathVariable Long id) {
        String email = authentication.getName();
        logger.info(UserControllerLogConstants.DELETE_ADDRESS_REQUEST, id);
        deleteAddressUseCase.execute(email, id);
        logger.info(UserControllerLogConstants.DELETE_ADDRESS_SUCCESS, id);
        return ResponseEntity.ok(UserAddressControllerMessages.ADDRESS_DELETED);
    }
}
