package org.pileka.hotel_api.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.test_util.HotelTestUtil;


public class HotelMapperTest {

    private final HotelMapper hotelMapper;

    HotelMapperTest() {
        this.hotelMapper = Mappers.getMapper(HotelMapper.class);
    }

    @Test
    void toShortDtoMapsCorrectly() {
        Hotel hotel = HotelTestUtil.getHotel();
        HotelTestUtil.assertDtoEqualsModel(hotelMapper.toShortDto(hotel), hotel);
    }

// TODO: inject mappers for embeddable types and finish the tests
//
//    @Test
//    void toDtoMapsCorrectly() {
//        Hotel hotel = HotelTestUtil.getHotel();
//        HotelTestUtil.assertDtoEqualsModel(hotelMapper.toDto(hotel), hotel);
//    }
//
//    @Test
//    void toModelMapsCorrectly() {
//        CreateHotelDTO createHotelDTO = HotelTestUtil.getCreateDto();
//        HotelTestUtil.assertModelEqualsDto(hotelMapper.toModel(createHotelDTO), createHotelDTO);
//    }
}
