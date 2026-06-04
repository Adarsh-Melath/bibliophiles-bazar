package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.usecase.GetAddressesUseCase;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.adarsh.backend.feature.user.infrastructure.repository.AddressRepositoryPort;
import com.adarsh.backend.feature.user.infrastructure.repository.UserCommandRepository;
import com.adarsh.backend.shared.application.dto.GetAddressResult;
import com.adarsh.backend.feature.user.domain.User;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class GetAddressesInteractor implements GetAddressesUseCase {
    private final UserCommandRepository userCommandRepository;
    private final AddressRepositoryPort addressRepositoryPort;

    @Override
    public List<GetAddressResult> execute(String email) {
        User user=userCommandRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email: "+email));

        return addressRepositoryPort.findByUserId(user.getId()).stream().map(address -> new GetAddressResult(
                address.getId(),
                address.getFullName(),
                address.getPhone(),
                address.getAddressLine(),
                address.getCity(),
                address.getState(),
                address.getPincode(),
                address.isDefault(),
                address.getAddressLine2(),
                address.getCountry(),
                address.getAddressType()
        )).toList();
    }
}

