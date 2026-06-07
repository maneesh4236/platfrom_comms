package com.platformcommons.sms.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentRequestDto {

    @NotBlank(message = "Student name is required")
    private String name;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Student code is required")
    private String studentCode;

    @Valid
    @NotEmpty(message = "At least one address is required")
    private List<AddressDto> addresses;
}
