package com.adarsh.backend.feature.user.application.interactor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.user.application.dto.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.dto.UpdateAddressResult;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.feature.user.application.usecase.UpdateAddressUseCase;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.application.port.AddressRepositoryPort;
import com.adarsh.backend.shared.domain.Address;
import com.adarsh.backend.shared.domain.exception.AddressNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateAddressInteractor implements UpdateAddressUseCase {

    private final UserCommandRepository userCommandRepository;

    private final AddressRepositoryPort addressRepositoryPort;

    @Override
    public UpdateAddressResult execute(String email, Long id, UpdateAddressCommand command) {
        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Address address = addressRepositoryPort.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new AddressNotFoundException("Address Not Found"));

        if (command.isDefault()) {
            List<Address> addresses = addressRepositoryPort.findByUserId(user.getId());
            for (Address add : addresses) {
                add.markAsNonDefault();
                addressRepositoryPort.save(add);
            }
        }

        address.updateAddress(command.getFullName(), command.getPhone(), command.getAddressLine(), command.getCity(), command.getState(), command.getPincode(), command.getAddressLine2(), command.getCountry(), command.getAddressType(), command.isDefault());

        addressRepositoryPort.save(address);

        return new UpdateAddressResult(address.getId(), address.getFullName(), address.getPhone(), address.getAddressLine(), address.getCity(), address.getState(), address.getPincode(), address.isDefault(), address.getAddressLine2(), address.getCountry(), address.getAddressType());
    }

}
