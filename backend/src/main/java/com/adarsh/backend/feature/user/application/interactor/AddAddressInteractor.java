package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.command.AddAddressCommand;
import com.adarsh.backend.feature.user.application.dto.result.AddAddressResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.AddAddressUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.application.port.AddressRepositoryPort;
import com.adarsh.backend.feature.user.domain.model.Address;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddAddressInteractor implements AddAddressUseCase {
    private static final Logger logger = LoggerFactory.getLogger(AddAddressInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final UserCommandRepository userCommandRepository;
    private final AddressRepositoryPort addressRepositoryPort;

    @Override
    public AddAddressResult execute(String email, AddAddressCommand command) {
        logger.info(UserInteractorLogConstants.ADD_ADDRESS_REQUEST, email);

        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        if (command.isDefault()) {
            List<Address> addresses = addressRepositoryPort.findByUserId(user.getId());

            for (Address address : addresses) {
                address.markAsNonDefault();
                addressRepositoryPort.save(address);
            }
        }

        Address newAddress = new Address.Builder().userId(user.getId()).fullName(command.fullName()).phone(command.phone()).addressLine(command.addressLine()).city(command.city()).state(command.state()).pincode(command.pincode()).isDefault(command.isDefault()).addressLine2(command.addressLine2()).country(command.country()).addressType(command.addressType()).build();

        Address savedAddress = addressRepositoryPort.save(newAddress);
        logger.info(UserInteractorLogConstants.ADD_ADDRESS_SUCCESS, savedAddress.getId());

        return new AddAddressResult(savedAddress.getId(), savedAddress.getFullName(), savedAddress.getPhone(), savedAddress.getAddressLine(), savedAddress.getCity(), savedAddress.getState(), savedAddress.getPincode(), savedAddress.isDefault(), savedAddress.getAddressLine2(), savedAddress.getCountry(), savedAddress.getAddressType());
    }
}
