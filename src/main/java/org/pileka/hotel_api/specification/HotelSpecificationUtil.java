package org.pileka.hotel_api.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.SearchHotelDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class HotelSpecificationUtil {

    public static Specification<Hotel> fromSearchDTO(SearchHotelDTO searchDTO) {
        if (searchDTO == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return Specification
                .where(hasName(searchDTO.getName()))
                .and(hasBrand(searchDTO.getBrand()))
                .and(hasCity(searchDTO.getCity()))
                .and(hasCountry(searchDTO.getCountry()))
                .and(hasAmenities(searchDTO.getAmenities()));
    }

    /**
     * Specification for filtering by hotel name (case-insensitive, partial match)
     */
    public static Specification<Hotel> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(name)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    /**
     * Specification for filtering by brand (case-insensitive, exact match)
     */
    public static Specification<Hotel> hasBrand(String brand) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(brand)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("brand")),
                    brand.toLowerCase()
            );
        };
    }

    /**
     * Specification for filtering by city (case-insensitive, exact match)
     * Accesses nested Address object
     */
    public static Specification<Hotel> hasCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(city)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("address").get("city")),
                    city.toLowerCase()
            );
        };
    }

    /**
     * Specification for filtering by country (case-insensitive, exact match)
     * Accesses nested Address object
     */
    public static Specification<Hotel> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(country)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("address").get("country")),
                    country.toLowerCase()
            );
        };
    }

    /**
     * Specification for filtering by amenities (case-insensitive, exact match)
     * Hotel must have ALL specified amenities (AND condition)
     */
    public static Specification<Hotel> hasAmenities(List<String> amenities) {
        return (root, query, criteriaBuilder) -> {
            if (amenities.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            // Use distinct to avoid duplicate results when multiple amenities match
            query.distinct(true);

            // Join the amenities collection
            Join<Object, Object> amenitiesJoin = root.join("amenities", JoinType.INNER);

            // Create predicate for each amenity
            List<Predicate> amenityPredicates = new ArrayList<>();
            for (String amenity : amenities) {
                if (StringUtils.hasText(amenity)) {
                    amenityPredicates.add(
                            criteriaBuilder.equal(
                                    amenitiesJoin.get("amenity"),
                                    amenity.toLowerCase()
                            )
                    );
                }
            }

            // Combine all amenity predicates with AND (hotel must have ALL specified amenities)
            return criteriaBuilder.and(amenityPredicates);
        };
    }
}
