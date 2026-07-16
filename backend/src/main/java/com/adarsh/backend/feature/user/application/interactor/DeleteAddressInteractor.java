package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.DeleteAddressUseCase;
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

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteAddressInteractor implements DeleteAddressUseCase {
    private static final Logger logger = LoggerFactory.getLogger(DeleteAddressInteractor.class);
    private final AddressRepositoryPort addressRepositoryPort;
    private final UserQueryRepository userQueryRepository;

    @Override
    public void execute(String email, Long addressId) {
        logger.info(UserInteractorLogConstants.DELETE_ADDRESS_REQUEST, addressId);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        Address address = addressRepositoryPort.findByIdAndUserId(addressId, user.getId()).orElseThrow(() -> new AddressNotFoundException(UserExceptionMessageConstants.ADDRESS_NOT_FOUND));

        addressRepositoryPort.delete(address);
        logger.info(UserInteractorLogConstants.DELETE_ADDRESS_SUCCESS, addressId);
    }
}
