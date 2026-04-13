package org.pileka.hotel_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * DTO representing a single value-count pair for histogram queries
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class HistogramDTO {
    private String columnValue;
    private Long count;
}
