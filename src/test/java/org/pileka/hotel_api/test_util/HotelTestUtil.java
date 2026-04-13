package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.CreateHotelDTO;
import org.pileka.hotel_api.dto.ResponseHotelDTO;
import org.pileka.hotel_api.dto.SearchHotelDTO;
import org.pileka.hotel_api.dto.ShortResponseHotelDTO;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class HotelTestUtil {
    public Hotel getHotel() {
        return Hotel.builder()
                .id(1L)
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(AddressTestUtil.getAddress())
                .contacts(ContactsTestUtil.getContacts())
                .arrivalTime(ArrivalTimeTestUtil.getArrivalTime())
                .amenities(getAmenities())
                .build();
    }

    public CreateHotelDTO getCreateDto() {
        return CreateHotelDTO.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms")
                .brand("Hilton")
                .address(AddressTestUtil.getAddressDTO())
                .contacts(ContactsTestUtil.getContactsDTO())
                .arrivalTime(ArrivalTimeTestUtil.getArrivalTimeDTO())
                .amenities(getAmenities())
                .build();
    }

    public ResponseHotelDTO getResponseHotelDTO() {
        return ResponseHotelDTO.builder()
                .id(1L)
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms")
                .brand("Hilton")
                .address(AddressTestUtil.getAddressDTO())
                .contacts(ContactsTestUtil.getContactsDTO())
                .arrivalTime(ArrivalTimeTestUtil.getArrivalTimeDTO())
                .amenities(getAmenities())
                .build();
    }

    public ShortResponseHotelDTO getShortResponseHotelDTO() {
        return ShortResponseHotelDTO.builder()
                .id(1L)
                .name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms")
                .address("9 Pobediteley Avenue, Minsk, 220004, Belarus")
                .phone("+375 17 309-80-00")
                .build();
    }

    public SearchHotelDTO getSearchHotelDTO() {
        return SearchHotelDTO.builder()
                .name("Hilton")
                .brand("Hilton")
                .city("Minsk")
                .country("Belarus")
                .amenities(Arrays.asList("Free WiFi", "Free parking", "Fitness center"))
                .build();
    }

    public List<String> getAmenities() {
        return Arrays.asList(
                "Free parking",
                "Free WiFi",
                "Non-smoking rooms",
                "Concierge",
                "On-site restaurant",
                "Fitness center",
                "Pet-friendly rooms",
                "Room service",
                "Business center",
                "Meeting rooms"
        );
    }
    





}
