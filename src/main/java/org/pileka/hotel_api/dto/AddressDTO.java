package org.pileka.hotel_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    @NotBlank(message = "House number is required")
    @Size(max = 20, message = "House number must not exceed 20 characters")
    private String houseNumber;

    @NotBlank(message = "Street is required")
    @Size(max = 100, message = "Street name must not exceed 100 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String city;

    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String country;

    @NotBlank(message = "Post code is required")
    @Size(max = 20, message = "Post code must not exceed 20 characters")
    private String postCode;
}