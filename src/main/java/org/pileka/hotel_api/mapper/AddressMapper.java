package org.pileka.hotel_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pileka.hotel_api.domain.Address;
import org.pileka.hotel_api.dto.AddressDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    AddressDTO toDto(Address model);

    Address toModel(AddressDTO dto);
}
