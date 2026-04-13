package org.pileka.hotel_api.test_util;

import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Address;
import org.pileka.hotel_api.dto.AddressDTO;

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
}
