package org.pileka.hotel_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.*;
import org.pileka.hotel_api.exception.EntityDoesntExistException;
import org.pileka.hotel_api.exception.InvalidInputException;
import org.pileka.hotel_api.mapper.HotelMapper;
import org.pileka.hotel_api.repository.HotelRepository;
import org.pileka.hotel_api.service.HotelService;
import org.pileka.hotel_api.specification.HotelSpecificationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;

    private final HotelMapper mapper;

    @Override
    public ShortResponseHotelDTO create(CreateHotelDTO createDto) {
        return mapper.toShortDto(
                repository.save(
                        mapper.toModel(createDto)
                )
        );
    }

    @Override
    public ResponseHotelDTO get(long id) {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new EntityDoesntExistException("Hotel with id " + id + "doesn't exist"))
        );
    }

    @Override
    public List<ShortResponseHotelDTO> get() {
        return repository.findAll().stream().map(mapper::toShortDto).toList();
    }

    @Override
    public List<ShortResponseHotelDTO> get(SearchHotelDTO searchDto) {
        return repository.findAll(
                HotelSpecificationUtil.fromSearchDTO(searchDto)
        ).stream().map(mapper::toShortDto).toList();
    }

    @Override
    public HistogramResponseDTO getHistogram(String fieldName) {
        String sanitizedFieldName = fieldName.strip().toLowerCase();
        if (FIELD_NAMES.contains(sanitizedFieldName)) {
            return new HistogramResponseDTO(repository.getHistogram(sanitizedFieldName));
        }
        else {
            throw new InvalidInputException(fieldName + " is not a recognized field name!");
        }
    }

    @Override
    public void addAmenities(long id, List<String> amenities) {
        if (amenities.isEmpty()) {
            throw new InvalidInputException("Amenities list can't be empty!");
        }

        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new EntityDoesntExistException("Hotel with id " + id + "doesn't exist"));

        hotel.setAmenities(
                Stream.concat(
                    hotel.getAmenities().stream(),
                    amenities.stream().filter(s -> !s.isBlank())
                ).distinct().toList()
        );

        repository.save(hotel);
    }
}
