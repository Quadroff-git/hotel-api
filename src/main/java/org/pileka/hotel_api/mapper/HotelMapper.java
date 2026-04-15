package org.pileka.hotel_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.pileka.hotel_api.domain.Address;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.CreateHotelDTO;
import org.pileka.hotel_api.dto.ResponseHotelDTO;
import org.pileka.hotel_api.dto.ShortResponseHotelDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AddressMapper.class, ArrivalTimeMapper.class, ContactsMapper.class})
public abstract class HotelMapper {

    @Mapping(target = "phone", source = "contacts.phone")
    public abstract ShortResponseHotelDTO toShortDto(Hotel model);

    public abstract ResponseHotelDTO toDto(Hotel model);

    public abstract Hotel toModel(CreateHotelDTO dto);

    // Could've changed Address#toString() but it feels like a hack considering all other entities use Lombok defaults
    protected String map(Address value) {
        return value.getHouseNumber() + " " + value.getStreet() + ", " + value.getCity() + ", " + value.getPostCode() + ", " + value.getCountry();
    }
}
