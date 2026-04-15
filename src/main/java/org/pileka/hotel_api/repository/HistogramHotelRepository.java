package org.pileka.hotel_api.repository;

import org.pileka.hotel_api.dto.HistogramItemDTO;
import java.util.List;

public interface HistogramHotelRepository {

    List<HistogramItemDTO> getHistogram(String columnName);
}
