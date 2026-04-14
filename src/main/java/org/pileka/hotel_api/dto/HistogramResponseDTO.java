package org.pileka.hotel_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A wrapper DTO for responses to histogram queries
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistogramResponseDTO {

    List<HistogramItemDTO> data;

}
