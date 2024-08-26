package com.rentcar.api.repository;

import com.rentcar.api.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
