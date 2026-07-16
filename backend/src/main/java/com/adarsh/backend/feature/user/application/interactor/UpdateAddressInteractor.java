package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.command.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.dto.result.UpdateAddressResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.UpdateAddressUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.application.port.AddressRepositoryPort;
import com.adarsh.backend.feature.user.domain.model.Address;
import com.adarsh.backend.feature.user.domain.exception.AddressNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAddressInteractor implements UpdateAddressUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UpdateAddressInteractor.class);
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final AddressRepositoryPort addressRepositoryPort;

    @Override
    public UpdateAddressResult execute(String email, Long id, UpdateAddressCommand command) {
        logger.info(UserInteractorLogConstants.UPDATE_ADDRESS_REQUEST, id, email);

        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Address address = addressRepositoryPort.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new AddressNotFoundException(UserExceptionMessageConstants.ADDRESS_NOT_FOUND));

        if (command.isDefault()) {
            List<Address> addresses = addressRepositoryPort.findByUserId(user.getId());

            for (Address add : addresses) {
                add.markAsNonDefault();
                addressRepositoryPort.save(add);
            }
        }

        address.updateAddress(command.fullName(), command.phone(), command.addressLine(), command.city(), command.state(), command.pincode(), command.addressLine2(), command.country(), command.addressType(), command.isDefault());

        Address savedAddress = addressRepositoryPort.save(address);
        logger.info(UserInteractorLogConstants.UPDATE_ADDRESS_SUCCESS, savedAddress.getId());

        return new UpdateAddressResult(savedAddress.getId(), savedAddress.getFullName(), savedAddress.getPhone(), savedAddress.getAddressLine(), savedAddress.getCity(), savedAddress.getState(), savedAddress.getPincode(), savedAddress.isDefault(), savedAddress.getAddressLine2(), savedAddress.getCountry(), savedAddress.getAddressType());
    }
}
