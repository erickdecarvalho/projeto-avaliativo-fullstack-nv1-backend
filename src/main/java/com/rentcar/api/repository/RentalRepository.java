package com.rentcar.api.repository;

import com.rentcar.api.model.Customer;
import com.rentcar.api.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
