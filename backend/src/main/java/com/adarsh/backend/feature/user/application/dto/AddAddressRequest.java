package com.adarsh.backend.feature.user.application.dto;

class AddAddressRequest {
    private String fullName;
    private String phone;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
    private boolean isDefault;
    private String addressLine2;
    private String country;
    private String addressType;

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