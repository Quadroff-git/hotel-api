package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.CreateHotelDTO;
import org.pileka.hotel_api.dto.ResponseHotelDTO;
import org.pileka.hotel_api.dto.SearchHotelDTO;
import org.pileka.hotel_api.dto.ShortResponseHotelDTO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
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
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
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
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
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

    public void assertDtoEqualsModel(ResponseHotelDTO dto, Hotel model) {
        if (dto == null && model == null) return;
        assertNotNull(dto, "ResponseHotelDTO should not be null");
        assertNotNull(model, "Hotel model should not be null");

        assertEquals(dto.getId(), model.getId());
        assertEquals(dto.getName(), model.getName());
        assertEquals(dto.getDescription(), model.getDescription());
        assertEquals(dto.getBrand(), model.getBrand());

        AddressTestUtil.assertDtoEqualsModel(dto.getAddress(), model.getAddress());
        ContactsTestUtil.assertDtoEqualsModel(dto.getContacts(), model.getContacts());
        ArrivalTimeTestUtil.assertDtoEqualsModel(dto.getArrivalTime(), model.getArrivalTime());

        assertIterableEquals(dto.getAmenities(), model.getAmenities());
    }

    public void assertDtoEqualsModel(ShortResponseHotelDTO dto, Hotel model) {
        if (dto == null && model == null) return;
        assertNotNull(dto, "ShortResponseHotelDTO should not be null");
        assertNotNull(model, "Hotel model should not be null");

        assertEquals(dto.getId(), model.getId());
        assertEquals(dto.getName(), model.getName());
        assertEquals(dto.getDescription(), model.getDescription());

        if (model.getContacts() != null) {
            assertEquals(dto.getPhone(), model.getContacts().getPhone());
        } else {
            assertNull(dto.getPhone());
        }

        if (model.getAddress() != null) {
            String expectedAddress = String.format("%s %s, %s, %s, %s",
                    model.getAddress().getHouseNumber(),
                    model.getAddress().getStreet(),
                    model.getAddress().getCity(),
                    model.getAddress().getPostCode(),
                    model.getAddress().getCountry());
            assertEquals(dto.getAddress(), expectedAddress);
        } else {
            assertNull(dto.getAddress());
        }
    }

    public void assertModelEqualsDto(Hotel model, CreateHotelDTO dto) {
        if (model == null && dto == null) return;
        assertNotNull(model, "Hotel model should not be null");
        assertNotNull(dto, "CreateHotelDTO should not be null");

        assertEquals(model.getName(), dto.getName());
        assertEquals(model.getDescription(), dto.getDescription());
        assertEquals(model.getBrand(), dto.getBrand());

        AddressTestUtil.assertModelEqualsDto(model.getAddress(), dto.getAddress());
        ContactsTestUtil.assertModelEqualsDto(model.getContacts(), dto.getContacts());
        ArrivalTimeTestUtil.assertModelEqualsDto(model.getArrivalTime(), dto.getArrivalTime());

        if (model.getAmenities() != null && dto.getAmenities() != null) {
            assertIterableEquals(model.getAmenities(), dto.getAmenities());
        }
    }
    





}
