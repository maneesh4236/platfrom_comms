package com.platformcommons.sms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class StudentProfileUpdateDto {

    @Email(message = "Email must be valid")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Mobile number must contain 10 digits"
    )
    private String mobileNumber;

    private String parentsName;

    @Valid
    private List<AddressDto> addresses;
}
