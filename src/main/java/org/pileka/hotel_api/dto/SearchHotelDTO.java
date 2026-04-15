package org.pileka.hotel_api.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHotelDTO {

    private String name;

    private String brand;

    private String city;

    private String country;

    private List<String> amenities;
}
