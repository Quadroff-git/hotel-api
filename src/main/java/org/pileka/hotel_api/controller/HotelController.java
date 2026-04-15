package org.pileka.hotel_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.pileka.hotel_api.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotel Management", description = "Endpoints for managing hotels")
public interface HotelController {

    @Operation(
            summary = "Create a new hotel",
            description = "Creates a new hotel with the provided information"
    )
    @PostMapping(path = "/hotels", produces = "application/json")
    ShortResponseHotelDTO create(@Valid @RequestBody CreateHotelDTO createDto);

    @Operation(
            summary = "Get hotel by ID",
            description = "Retrieves detailed information about a specific hotel by its ID"
    )
    @GetMapping(path = "/hotels/{id}", produces = "application/json")
    ResponseHotelDTO get(@Parameter(description = "Hotel ID", required = true) @PathVariable long id);

    @Operation(
            summary = "Get all hotels",
            description = "Retrieves a list of all hotels with basic information"
    )
    @GetMapping(path = "/hotels", produces = "application/json")
    List<ShortResponseHotelDTO> get();

    @Operation(
            summary = "Search hotels",
            description = "Search hotels by name, brand, city, country, and/or amenities"
    )
    @GetMapping(path = "/search", produces = "application/json")
    List<ShortResponseHotelDTO> get(@Valid SearchHotelDTO searchDto);

    @Operation(
            summary = "Get histogram statistics",
            description = "Retrieves count of hotels grouped by specified field (brand, name, city, or country)"
    )
    @GetMapping(path = "/histogram/{param}", produces = "application/json")
    HistogramResponseDTO getHistogram(
            @Parameter(description = "Field to group by (brand, name, city, or country)", required = true)
            @PathVariable String param
    );

    @Operation(
            summary = "Add amenities to hotel",
            description = "Adds one or more amenities to an existing hotel"
    )
    @PostMapping(path = "/hotels/{id}/amenities", produces = "application/json")
    void addAmenities(
            @Parameter(description = "Hotel ID", required = true) @PathVariable long id,
            @Parameter(description = "List of amenities to add", required = true) @RequestBody List<String> amenities
    );
}