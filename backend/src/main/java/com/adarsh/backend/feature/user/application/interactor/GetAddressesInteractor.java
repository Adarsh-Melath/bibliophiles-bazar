package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.result.GetAddressResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.usecase.GetAddressesUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.exception.constant.UserExceptionMessageConstants;
import com.adarsh.backend.feature.user.application.port.AddressRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAddressesInteractor implements GetAddressesUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetAddressesInteractor.class);
    private final UserQueryRepository userQueryRepository;
    private final AddressRepositoryPort addressRepositoryPort;

    @Override
    public List<GetAddressResult> execute(String email) {
        logger.info(UserInteractorLogConstants.GET_ADDRESSES_REQUEST, email);
        User user = userQueryRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(UserExceptionMessageConstants.USER_NOT_FOUND));

        List<GetAddressResult> result = addressRepositoryPort.findByUserId(user.getId()).stream().map(address -> new GetAddressResult(address.getId(), address.getFullName(), address.getPhone(), address.getAddressLine(), address.getCity(), address.getState(), address.getPincode(), address.isDefault(), address.getAddressLine2(), address.getCountry(), address.getAddressType())).toList();

        if (result.isEmpty()) {
            logger.debug(UserInteractorLogConstants.GET_ADDRESSES_EMPTY, user.getId());
        } else {
            logger.info(UserInteractorLogConstants.GET_ADDRESSES_FETCHED, user.getId());
        }

        return result;
    }
}

