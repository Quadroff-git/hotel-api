package org.pileka.hotel_api.repository.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.pileka.hotel_api.dto.HistogramDTO;
import org.pileka.hotel_api.repository.HistogramHotelRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HistogramHotelRepositoryImpl implements HistogramHotelRepository {

    private final EntityManager entityManager;

    // Since it's impossible to have a single @Query custom method in HotelRepository because of the need to join,
    // (technically possible but requires unnecessary joins on most histogram requests) I've decided to add a
    // custom fragment repository interface and implementation so that the resulting HotelRepository only has one method
    // for all its histogram needs
    @Override
    public List<HistogramDTO> getHistogram(String columnName) {
        if (columnName.equals("amenities")) {
            return getAmenitiesHistogram();
        }
        else {
            return getColumnHistogram(columnName);
        }
    }

    private List<HistogramDTO> getColumnHistogram(String columnName) {
        return entityManager.createQuery("SELECT new org.pileka.hotel_api.dto.HistogramDTO(" +
                "  CASE WHEN :columnName = 'brand' THEN h.brand " +
                "       WHEN :columnName = 'city' THEN h.address.city " +
                "       WHEN :columnName = 'country' THEN h.address.country " +
                "       ELSE 'error'" +
                "  END, " +
                "  COUNT(h)" +
                ") " +
                "FROM Hotel h " +
                "GROUP BY " +
                "  CASE WHEN :columnName = 'brand' THEN h.brand " +
                "       WHEN :columnName = 'city' THEN h.address.city " +
                "       WHEN :columnName = 'country' THEN h.address.country " +
                "       ELSE 'error'" +
                "  END", HistogramDTO.class).setParameter("columnName", columnName).getResultList();
    }

    private List<HistogramDTO> getAmenitiesHistogram() {
        return entityManager.createQuery("SELECT new org.pileka.hotel_api.dto.HistogramDTO(a, COUNT(h)) " +
                "FROM Hotel h JOIN h.amenities a " +
                "GROUP BY a", HistogramDTO.class).getResultList();
    }

}
