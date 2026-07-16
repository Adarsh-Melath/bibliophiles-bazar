package com.adarsh.backend;

import com.adarsh.backend.feature.user.application.dto.command.UpdateAddressCommand;
import com.adarsh.backend.feature.user.application.port.AddressRepositoryPort;
import com.adarsh.backend.feature.user.domain.model.Address;
import com.adarsh.backend.feature.user.infrastructure.persistence.jparepository.AddressJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(properties = {
        "spring.cloud.vault.enabled=false"
})
class BackendApplicationTests {

    @Autowired
    private AddressRepositoryPort addressRepositoryPort;

    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

    @Autowired
    private AddressJpaRepository addressJpaRepository;

    @Test
    void testAddressUpdate() {
        var entities = addressJpaRepository.findAll();
        if (!entities.isEmpty()) {
            var entity = entities.get(0);
            System.out.println("=== TEST START ===");
            System.out.println("Address ID: " + entity.getId());
            System.out.println("Current type: " + entity.getAddressType());
            System.out.println("Current isDefault: " + entity.getIsDefault());

            Optional<Address> opt = addressRepositoryPort.findByIdAndUserId(entity.getId(), entity.getUser().getId());
            if (opt.isPresent()) {
                Address address = opt.get();
                // Toggle values: WORK -> HOME or HOME -> WORK, default -> non-default
                String newType = "WORK".equals(address.getAddressType()) ? "HOME" : "WORK";
                boolean newDefault = !address.isDefault();
                
                System.out.println("Updating to type: " + newType + ", isDefault: " + newDefault);
                address.updateAddress(address.getFullName(), address.getPhone(), address.getAddressLine(),
                    address.getCity(), address.getState(), address.getPincode(), address.getAddressLine2(),
                    address.getCountry(), newType, newDefault);
                addressRepositoryPort.save(address);
                
                Address updated = addressRepositoryPort.findByIdAndUserId(entity.getId(), entity.getUser().getId()).get();
                System.out.println("Updated type in database: " + updated.getAddressType());
                System.out.println("Updated isDefault in database: " + updated.isDefault());
            }
            System.out.println("=== TEST END ===");
        } else {
            System.out.println("=== No addresses found in the database to test! ===");
        }
    }

    @Test
    void testJsonDeserialization() throws Exception {
        String json = "{\"fullName\":\"John Doe\",\"phone\":\"1234567890\",\"addressLine\":\"123 Street\",\"city\":\"City\",\"state\":\"State\",\"pincode\":\"12345\",\"isDefault\":false,\"addressLine2\":\"Apt 1\",\"country\":\"Country\",\"addressType\":\"WORK\"}";
        UpdateAddressCommand command = objectMapper.readValue(json, UpdateAddressCommand.class);
        System.out.println("=== DESERIALIZATION TEST START ===");
        System.out.println("addressType value: " + command.addressType());
        System.out.println("=== DESERIALIZATION TEST END ===");
    }

}
