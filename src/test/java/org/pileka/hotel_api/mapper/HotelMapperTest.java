package org.pileka.hotel_api.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.CreateHotelDTO;
import org.pileka.hotel_api.test_util.HotelTestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// HotelMapper uses @Mapper(uses = {...}), and because of this the impl requires a Spring DI container to use
@SpringBootTest(classes = {HotelMapperImpl.class,
        AddressMapperImpl.class,
        ArrivalTimeMapperImpl.class,
        ContactsMapperImpl.class})
public class HotelMapperTest {

    @Autowired
    HotelMapper hotelMapper;

    @Test
    void toShortDtoMapsCorrectly() {
        Hotel hotel = HotelTestUtil.getHotel();
        HotelTestUtil.assertDtoEqualsModel(hotelMapper.toShortDto(hotel), hotel);
    }

    @Test
    void toDtoMapsCorrectly() {
        Hotel hotel = HotelTestUtil.getHotel();
        HotelTestUtil.assertDtoEqualsModel(hotelMapper.toDto(hotel), hotel);
    }

    @Test
    void toModelMapsCorrectly() {
        CreateHotelDTO createHotelDTO = HotelTestUtil.getCreateDto();
        HotelTestUtil.assertModelEqualsDto(hotelMapper.toModel(createHotelDTO), createHotelDTO);
    }
}
