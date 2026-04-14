package org.pileka.hotel_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.*;
import org.pileka.hotel_api.exception.EntityDoesntExistException;
import org.pileka.hotel_api.mapper.HotelMapper;
import org.pileka.hotel_api.repository.HotelRepository;
import org.pileka.hotel_api.service.HotelService;
import org.pileka.hotel_api.specification.HotelSpecificationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<HistogramDTO> getHistogram(String fieldName) {
        if (FIELD_NAMES.contains(fieldName.strip().toLowerCase())) {
            return repository.getHistogram(fieldName);
        }
        else {
            throw new IllegalArgumentException(fieldName + " is not a recognized field name!");
        }
    }

    @Override
    public void addAmenities(long id, List<String> amenities) {
        Hotel hotel = repository.findById(id)
                .orElseThrow(() -> new EntityDoesntExistException("Hotel with id " + id + "doesn't exist"));

        hotel.getAmenities().addAll(amenities);

        repository.save(hotel);
    }
}
