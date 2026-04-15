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
    private String name;

    private String description;

    @NotBlank(message = "Brand is required")
    private String brand;

    @Valid
    @NotNull(message = "Address is required")
    private AddressDTO address;

    @Valid
    @NotNull(message = "Contacts are required")
    private ContactsDTO contacts;

    @Valid
    private ArrivalTimeDTO arrivalTime;

    private List<@NotBlank String> amenities;
}
