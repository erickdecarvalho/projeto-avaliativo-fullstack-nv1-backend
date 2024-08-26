package com.rentcar.api.dto;

import com.rentcar.api.model.VehicleType;

public record VehicleCreateResponseDTO(
        Long id,
        String name,
        VehicleType vehicleType,
        String chassi,
        Integer year,
        String color,
        Boolean isRented
) {
}
