package com.platformcommons.sms.dto;

import com.platformcommons.sms.entity.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {

    @NotNull(message = "Address type is required")
    private AddressType addressType;

    @NotBlank(message = "Address line is required")
    private String addressLine;
}
