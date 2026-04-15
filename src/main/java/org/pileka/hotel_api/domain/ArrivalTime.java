package org.pileka.hotel_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArrivalTime {

    @Column(name = "check_in_time")
    private String checkIn;

    @Column(name = "check_out_time")
    private String checkOut;
}
