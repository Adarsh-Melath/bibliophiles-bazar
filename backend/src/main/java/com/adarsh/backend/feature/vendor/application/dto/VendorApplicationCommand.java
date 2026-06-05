package com.adarsh.backend.feature.vendor.application.dto;

import java.util.Objects;

public class VendorApplicationCommand {

    private final String name;
    private final String email;
    private final String phone;
    private final String businessName;
    private final String businessDescription;
    private final String category;
    private final String businessRegistrationNumber;
    private final String website;
    private final String publishingSince;

    public VendorApplicationCommand(String name,
            String email,
            String phone,
            String businessName,
            String businessDescription,
            String category,
            String businessRegistrationNumber,
            String website,
            String publishingSince) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(phone, "Phone cannot be null");
        Objects.requireNonNull(businessName, "Business name cannot be null");
        Objects.requireNonNull(businessDescription, "Business description cannot be null");
        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(businessRegistrationNumber, "Business registration number cannot be null");

        if (name.isBlank() || name.length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!phone.matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (businessName.isBlank() || businessName.length() < 2) {
            throw new IllegalArgumentException("Business name must be at least 2 characters");
        }
        if (businessDescription.isBlank() || businessDescription.length() < 10) {
            throw new IllegalArgumentException("Business description must be at least 10 characters");
        }
        if (category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
        if (businessRegistrationNumber.isBlank()) {
            throw new IllegalArgumentException("Business registration number cannot be empty");
        }

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.businessName = businessName;
        this.businessDescription = businessDescription;
        this.category = category;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.website = website;
        this.publishingSince = publishingSince;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public String getCategory() {
        return category;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getPublishingSince() {
        return publishingSince;
    }
}
