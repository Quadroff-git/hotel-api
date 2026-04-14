package org.pileka.hotel_api.controller.impl;

import lombok.RequiredArgsConstructor;
import org.pileka.hotel_api.controller.HotelController;
import org.pileka.hotel_api.dto.*;
import org.pileka.hotel_api.service.HotelService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelControllerImpl implements HotelController {

    private final HotelService service;

    @Override
    public ShortResponseHotelDTO create(CreateHotelDTO createDto) {
        return service.create(createDto);
    }

    @Override
    public ResponseHotelDTO get(long id) {
        return service.get(id);
    }

    @Override
    public List<ShortResponseHotelDTO> get() {
        return service.get();
    }

    @Override
    public List<ShortResponseHotelDTO> get(SearchHotelDTO searchDto) {
        return service.get(searchDto);
    }

    @Override
    public HistogramResponseDTO getHistogram(String param) {
        return service.getHistogram(param);
    }

    @Override
    public void addAmenities(long id, List<String> amenities) {
        service.addAmenities(id, amenities);
    }
}
