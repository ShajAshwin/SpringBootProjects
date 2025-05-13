package com.example.AddressBook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class ContactDTO {


    @NotBlank(message = "Name cannot be Empty")
    private String name;
//    @Pattern(regexp = "^\\d{10}$", message = "The field must be 10 digits long")
    @Pattern(regexp = "^\\d{10}$", message = "The field must be 10 digits long")
    private Long mobileNumber;
    @Email(message = "Invalid Email address")
    private String emailID;
    @NotBlank
    private String address;

    public ContactDTO(String name, Long mobileNumber, String emailID, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.address = address;
    }

    // Factory method to create ContactDTO instance
    public static ContactDTO build(String name, Long mobileNumber, String emailID, String address) {
        return new ContactDTO(name, mobileNumber, emailID, address);
    }
}
