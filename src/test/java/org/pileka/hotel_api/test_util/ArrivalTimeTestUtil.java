package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.ArrivalTime;
import org.pileka.hotel_api.dto.ArrivalTimeDTO;

@UtilityClass
public class ArrivalTimeTestUtil {

    public ArrivalTime getArrivalTime() {
        return ArrivalTime.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();
    }

    public ArrivalTimeDTO getArrivalTimeDTO() {
        return ArrivalTimeDTO.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();
    }
}
