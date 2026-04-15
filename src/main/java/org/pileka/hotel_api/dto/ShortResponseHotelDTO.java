package org.pileka.hotel_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for responses to GET /hotels, GET /search, POST /hotels endpoints
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortResponseHotelDTO {

    private Long id;

    private String name;

    private String description;

    private String address;

    private String phone;
}
