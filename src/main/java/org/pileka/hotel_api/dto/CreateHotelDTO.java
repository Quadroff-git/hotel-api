package org.pileka.hotel_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for POST /hotels requests' bodies
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateHotelDTO {

    @NotBlank(message = "Hotel name is required")
    @Size(min = 2, max = 200, message = "Hotel name must be between 2 and 200 characters")
    private String name;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;

    @NotBlank(message = "Brand is required")
    @Size(max = 100, message = "Brand name must not exceed 100 characters")
    private String brand;

    @Valid
    @NotNull(message = "Address is required")
    private AddressDTO address;

    @Valid
    @NotNull(message = "Contacts are required")
    private ContactsDTO contacts;

    @Valid
    private ArrivalTimeDTO arrivalTime;

    @Size(max = 30, message = "Cannot have more than 30 amenities")
    private List<@NotBlank @Size(min = 2, max = 100) String> amenities;
}
