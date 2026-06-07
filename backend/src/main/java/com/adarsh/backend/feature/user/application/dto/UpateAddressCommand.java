package com.adarsh.backend.feature.user.application.dto;

import java.util.Objects;

public class UpateAddressCommand {
     private final String fullName;
    private final String phone;
    private final String addressLine;
    private final String city;
    private final String state;
    private final String pincode;
    private final boolean isDefault;
    private final String addressLine2;
    private final String country;
    private final String addressType;

    public UpdateAddressCommand(String fullName,String phone,String addressLine,String city,String state,String pincode,boolean isDefault,String addressLine2,String country,String addressType){
        Objects.requireNonNull(fullName, "Full name cannot be null");
        Objects.requireNonNull(phone, "Phone cannot be null");
        Objects.requireNonNull(addressLine, "Address line cannot be null");
        Objects.requireNonNull(city, "City cannot be null");
        Objects.requireNonNull(state, "State cannot be null");
        Objects.requireNonNull(pincode, "Pincode cannot be null");
        Objects.requireNonNull(country, "Country cannot be null");
        Objects.requireNonNull(addressType, "Address type cannot be null");
        
        if (fullName.isBlank() || fullName.length() < 2) {
            throw new IllegalArgumentException("Full name must be at least 2 characters");
        }
        if (!phone.matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (addressLine.isBlank() || addressLine.length() < 5) {
            throw new IllegalArgumentException("Address line must be at least 5 characters");
        }
        if (city.isBlank() || city.length() < 2) {
            throw new IllegalArgumentException("City must be at least 2 characters");
        }
        if (state.isBlank() || state.length() < 2) {
            throw new IllegalArgumentException("State must be at least 2 characters");
        }
        if (!pincode.matches("^[0-9]{5,10}$")) {
            throw new IllegalArgumentException("Invalid pincode format");
        }
        if (country.isBlank() || country.length() < 2) {
            throw new IllegalArgumentException("Country must be at least 2 characters");
        }
        if (addressType.isBlank()) {
            throw new IllegalArgumentException("Address type cannot be empty");
        }
        
        this.fullName=fullName;
        this.phone=phone;
        this.addressLine=addressLine;
        this.city=city;
        this.state=state;
        this.pincode=pincode;
        this.isDefault=isDefault;
        this.addressLine2=addressLine2;
        this.country=country;
        this.addressType=addressType;
    }

    public String getFullName(){
        return fullName;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddressLine(){
        return addressLine;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getPincode(){
        return pincode;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCountry() {
        return country;
    }

    public String getAddressType() {
        return addressType;
    }
}
