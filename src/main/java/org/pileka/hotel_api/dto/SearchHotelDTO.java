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

    @Size(max = 100, message = "Hotel name must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-',&]+$", message = "Hotel name contains invalid characters")
    private String name;

    @Size(max = 50, message = "Brand name must not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s\\-',&]+$", message = "Brand name contains invalid characters")
    private String brand;

    @Size(max = 100, message = "City name must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "City name contains invalid characters")
    private String city;

    @Size(max = 100, message = "Country name must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "Country name contains invalid characters")
    private String country;

    @Size(max = 20, message = "Cannot search for more than 20 amenities at once")
    private List<
            @Size(min = 2, max = 50, message = "Each amenity name must be between 2 and 50 characters")
            @Pattern(regexp = "^[a-zA-Z0-9\\s\\-']+$", message = "Amenity contains invalid characters")
                    String
            > amenities;
}
