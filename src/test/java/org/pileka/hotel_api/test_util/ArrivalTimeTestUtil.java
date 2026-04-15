package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.ArrivalTime;
import org.pileka.hotel_api.dto.ArrivalTimeDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    public void assertDtoEqualsModel(ArrivalTimeDTO dto, ArrivalTime model) {
        if (dto == null && model == null) return;
        assertNotNull(dto, "ArrivalTimeDTO should not be null");
        assertNotNull(model, "ArrivalTime model should not be null");
        assertEquals(dto.getCheckIn(), model.getCheckIn());
        assertEquals(dto.getCheckOut(), model.getCheckOut());
    }

    public void assertModelEqualsDto(ArrivalTime model, ArrivalTimeDTO dto) {
        if (model == null && dto == null) return;
        assertNotNull(model, "ArrivalTime model should not be null");
        assertNotNull(dto, "ArrivalTimeDTO should not be null");
        assertEquals(model.getCheckIn(), dto.getCheckIn());
        assertEquals(model.getCheckOut(), dto.getCheckOut());
    }
}
