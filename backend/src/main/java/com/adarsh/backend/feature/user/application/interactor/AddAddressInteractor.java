package com.adarsh.backend.feature.user.application.interactor;

import org.springframework.stereotype.Service;
import com.adarsh.backend.feature.user.application.usecase.AddAddressUseCase;
import lombok.RequiredArgsConstructor;
import com.adarsh.backend.feature.user.application.port.UserCommandRepository;
import com.adarsh.backend.shared.application.port.AddressRepositoryPort;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.application.dto.AddAddressCommand;
import com.adarsh.backend.feature.user.application.dto.AddAddressResult;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.shared.domain.Address;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddAddressInteractor implements AddAddressUseCase {

    private final UserCommandRepository userCommandRepository;
    private final AddressRepositoryPort addressRepositoryPort;

    @Override
    public AddAddressResult execute(String email, AddAddressCommand command) {
        User user = userCommandRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email "));

        if (command.isDefault()) {
            List<Address> addresses = addressRepositoryPort.findByUserId(user.getId());

            for (Address address : addresses) {
                address.markAsNonDefault();
                addressRepositoryPort.save(address);
            }
        }

        Address newAddress = new Address.Builder()
                .userId(user.getId())
                .fullName(command.getFullName())
                .phone(command.getPhone())
                .addressLine(command.getAddressLine())
                .city(command.getCity())
                .state(command.getState())
                .pincode(command.getPincode())
                .isDefault(command.isDefault())
                .addressLine2(command.getAddressLine2())
                .country(command.getCountry())
                .addressType(command.getAddressType())
                .build();

        addressRepositoryPort.save(newAddress);

        return new AddAddressResult(newAddress.getId(), newAddress.getFullName(), newAddress.getPhone(), newAddress.getAddressLine(), newAddress.getCity(), newAddress.getState(), newAddress.getPincode(), newAddress.isDefault(), newAddress.getAddressLine2(), newAddress.getCountry(), newAddress.getAddressType());
    }
}
