package com.adarsh.backend;

import com.adarsh.backend.feature.user.application.dto.command.UpdateAddressCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class DeserializationTest {
    @Test
    void testJsonDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"fullName\":\"John Doe\",\"phone\":\"+919876543210\",\"addressLine\":\"123 Street Address\",\"city\":\"CityName\",\"state\":\"StateName\",\"pincode\":\"123456\",\"isDefault\":false,\"addressLine2\":\"Apt 1\",\"country\":\"CountryName\",\"addressType\":\"WORK\"}";
        UpdateAddressCommand command = objectMapper.readValue(json, UpdateAddressCommand.class);
        System.out.println("=== DESERIALIZATION TEST START ===");
        System.out.println("isDefault value: " + command.isDefault());
        System.out.println("addressType value: " + command.addressType());
        System.out.println("=== DESERIALIZATION TEST END ===");
    }
}
