package org.pileka.hotel_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for responses to GET /hotels/{id} endpoint
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseHotelDTO {
    private Long id;

    private String name;

    private String description;

    private String brand;

    private AddressDTO address;

    private ContactsDTO contacts;

    private ArrivalTimeDTO arrivalTime;

    private List<String> amenities;
}