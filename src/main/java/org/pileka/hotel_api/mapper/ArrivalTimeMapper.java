package org.pileka.hotel_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.pileka.hotel_api.domain.ArrivalTime;
import org.pileka.hotel_api.dto.ArrivalTimeDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArrivalTimeMapper {
    ArrivalTimeDTO toDto(ArrivalTime model);

    ArrivalTime toModel(ArrivalTimeDTO dto);
}