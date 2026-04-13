package org.pileka.hotel_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalTimeDTO {

    @NotNull(message = "Check-in time is required")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Check-in time must be in HH:mm format")
    private String checkIn;

    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Check-out time must be in HH:mm format")
    private String checkOut; // Optional
}
