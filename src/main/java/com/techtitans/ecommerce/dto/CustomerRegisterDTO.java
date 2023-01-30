package com.techtitans.ecommerce.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerRegisterDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    private LocalDate birthDate;

    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
