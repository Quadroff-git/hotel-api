package org.pileka.hotel_api.repository;

import org.pileka.hotel_api.domain.Hotel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.ListCrudRepository;

public interface HotelRepository extends ListCrudRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> { }
