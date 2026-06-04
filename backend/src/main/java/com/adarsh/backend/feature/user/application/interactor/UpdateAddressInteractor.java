package com.adarsh.backend.feature.user.application.interactor;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.adarsh.backend.feature.user.application.port.in.UpdateAddressUseCase;

import com.adarsh.backend.feature.user.application.port.out.AddressRepositoryPort;
import com.adarsh.backend.feature.user.application.port.out.UserCommandRepository;
import com.adarsh.backend.feature.user.domain.Address;
import com.adarsh.backend.feature.user.domain.User;
import com.adarsh.backend.feature.user.domain.exception.UserNotFoundException;
import com.adarsh.backend.feature.user.application.dto.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.dto.UpdateAddressResult;
import com.adarsh.backend.feature.user.applicatio
n.dto.UpdateAddressResult;
import com.adarsh.backend.feature.user.domain.exception.AddressNotFoundException;
import java.util.List;



@Service
@RequiredArgsConstructor
public interface UpdateAddressInteractor 

    implements UpdateAddressUseCase {
    private final UserCommandRepository userCommandRepository;

    private final AddressRepositoryPort addressRepositoryPort;

    @Override 
    public UpdateAddressResult execute(String email,Long id,UpdateAddressCommand command){
        User user=userCommandRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User Not Found"));

        Address address=addressRepositoryPort.findById(id).orElseThrow(()->new AddressNotFoundException("Address Not Found"));

        if(command.isDefault()){
            List<Address> addresses=addressRepositoryPort.findByUserId(user.getId());
            for(Address address:addresses){
                address.markAsNonDefault();
                addressRepositoryPort.save(address);
            }
        }

        address.updateAddress(command.getFullName(),command.getPhone(),command.getAddressLine(),command.getCity(),command.getState(),command.getPincode(),command.getAddressLine2(),command.getCountry(),command.getAddressType(),command.isDefault());

        addressRepositoryPort.save(address);

        return new UpdateAddressResult(address.getId(),address.getFullName(),address.getPhone(),address.getAddressLine(),address.getCity(),address.getState(),address.getPincode(),address.getAddressLine2(),address.getCountry(),address.getAddressType(),address.isDefault());
    }

}
