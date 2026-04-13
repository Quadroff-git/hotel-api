package org.pileka.hotel_api.repository;

import org.pileka.hotel_api.dto.HistogramDTO;
import java.util.List;

public interface HistogramHotelRepository {

    List<HistogramDTO> getHistogram(String columnName);
}
