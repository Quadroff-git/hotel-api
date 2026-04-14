package org.pileka.hotel_api.controller;

import jakarta.validation.Valid;
import org.pileka.hotel_api.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface HotelController {

    @PostMapping(path = "/hotels")
    ShortResponseHotelDTO create(@Valid @RequestBody CreateHotelDTO createDto);

    @GetMapping(path = "/hotels/{id}")
    ResponseHotelDTO get(@PathVariable long id);

    @GetMapping(path = "/hotels")
    List<ShortResponseHotelDTO> get();

    @GetMapping(path = "/search")
    List<ShortResponseHotelDTO> get(@Valid SearchHotelDTO searchDto);

    @GetMapping(path = "/histogram/{param}")
    List<HistogramItemDTO> getHistogram(@PathVariable String param);

    @PostMapping(path = "/hotels/{id}/amenities")
    void addAmenities(@PathVariable long id, @RequestBody List<String> amenities);

}
