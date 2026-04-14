package org.pileka.hotel_api.service;

import org.pileka.hotel_api.dto.*;

import java.util.List;

public interface HotelService {

    ShortResponseHotelDTO create(CreateHotelDTO createDto);

    ResponseHotelDTO get(long id);

    List<ShortResponseHotelDTO> get();

    List<ShortResponseHotelDTO> get(SearchHotelDTO searchDto);

    HistogramResponseDTO getHistogram(String fieldName);

    /**
     * Add a list of amenities to the existing amenities of the specified hotel
     * @param id id of the hotel to add amenities to
     * @param amenities strings representing the amenities
     */
    void addAmenities(long id, List<String> amenities);
}
