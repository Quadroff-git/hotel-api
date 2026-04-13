package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Address;
import org.pileka.hotel_api.dto.AddressDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@UtilityClass
public class AddressTestUtil {

    public Address getAddress() {
        return Address.builder()
                .houseNumber("9")
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004")
                .build();
    }

    public AddressDTO getAddressDTO() {
        return AddressDTO.builder()
                .houseNumber("9")
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004")
                .build();
    }

    public void assertDtoEqualsModel(AddressDTO dto, Address model) {
        if (dto == null && model == null) return;
        assertNotNull(dto, "AddressDTO should not be null");
        assertNotNull(model, "Address model should not be null");
        assertEquals(dto.getHouseNumber(), model.getHouseNumber());
        assertEquals(dto.getStreet(), model.getStreet());
        assertEquals(dto.getCity(), model.getCity());
        assertEquals(dto.getCountry(), model.getCountry());
        assertEquals(dto.getPostCode(), model.getPostCode());
    }

    public void assertModelEqualsDto(Address model, AddressDTO dto) {
        if (model == null && dto == null) return;
        assertNotNull(model, "Address model should not be null");
        assertNotNull(dto, "AddressDTO should not be null");
        assertEquals(model.getHouseNumber(), dto.getHouseNumber());
        assertEquals(model.getStreet(), dto.getStreet());
        assertEquals(model.getCity(), dto.getCity());
        assertEquals(model.getCountry(), dto.getCountry());
        assertEquals(model.getPostCode(), dto.getPostCode());
    }
}
