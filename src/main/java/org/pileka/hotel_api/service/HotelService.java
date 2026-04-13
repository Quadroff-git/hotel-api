package org.pileka.hotel_api.service;

import org.pileka.hotel_api.dto.CreateHotelDTO;
import org.pileka.hotel_api.dto.HistogramDTO;
import org.pileka.hotel_api.dto.SearchHotelDTO;
import org.pileka.hotel_api.dto.ShortResponseHotelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {

    ShortResponseHotelDTO create(CreateHotelDTO createDto);

    ShortResponseHotelDTO get(long id);

    Page<ShortResponseHotelDTO> get(Pageable pageable);

    Page<ShortResponseHotelDTO> get(SearchHotelDTO searchDto, Pageable pageable);

    List<HistogramDTO> getHistogram(String fieldName);

    /**
     * Add a list of amenities to the existing amenities of the specified hotel
     * @param id id of the hotel to add amenities to
     * @param amenities strings representing the amenities
     */
    void addAmenities(long id, List<String> amenities);
}
