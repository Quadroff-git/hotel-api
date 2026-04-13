package org.pileka.hotel_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing a single value-count pair for histogram queries
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistogramDTO {
    private String columnValue;
    private Long count;
}
