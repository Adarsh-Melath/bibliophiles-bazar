package com.adarsh.backend.feature.user.application.interactor;
import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.usecase.DeleteAddressUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.shared.application.port.AddressRepositoryPort;
import com.adarsh.backend.shared.domain.Address;
import com.adarsh.backend.shared.domain.exception.AddressNotFoundException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DeleteAddressInteractor implements DeleteAddressUseCase {
    private final AddressRepositoryPort addressRepositoryPort;

    private final UserCommandRepository userCommandRepository;

    @Override
    public void execute(String email, Long addressId) {
        User user=userCommandRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email: ");

        Address address=addressRepositoryPort.findByIdAndUserId( addressId,user.getId()).orElseThrow(()->new AddressNotFoundException("Address not found with id: "+addressId));

        addressRepositoryPort.delete(address);
    }
}
