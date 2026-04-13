package org.pileka.hotel_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pileka.hotel_api.domain.Contacts;
import org.pileka.hotel_api.dto.ContactsDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactsMapper {
    ContactsDTO toDto(Contacts model);

    Contacts toModel(ContactsDTO dto);
}
