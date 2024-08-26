package com.rentcar.api.mapper;

import com.rentcar.api.dto.RegisterVehicleDTO;
import com.rentcar.api.dto.VehicleCreateResponseDTO;
import com.rentcar.api.model.Vehicle;

public class VehicleMapper {
    public static Vehicle registerVehicleDTOToVehicle(RegisterVehicleDTO dto) {
        return Vehicle.builder()
                .name(dto.name())
                .vehicleType(dto.vehicleType())
                .chassi(dto.chassi())
                .year(dto.year())
                .color(dto.color())
                .build();
    }

    public static VehicleCreateResponseDTO vehicleToVehicleCreateResponseDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        return new VehicleCreateResponseDTO(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getVehicleType(),
                vehicle.getChassi(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getIsRented()
        );
    }
}
