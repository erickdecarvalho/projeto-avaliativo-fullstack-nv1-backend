package com.rentcar.api.model;

import com.rentcar.api.dto.RegisterVehicleDTO;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String chassi;
    private Integer year;
    private String color;
    private Boolean isRented;

    public Vehicle(RegisterVehicleDTO registerVehicleDTO) {
        this.name = registerVehicleDTO.name();
        this.vehicleType = registerVehicleDTO.vehicleType();
        this.chassi = registerVehicleDTO.chassi();
        this.year = registerVehicleDTO.year();
        this.color = registerVehicleDTO.color();
        this.isRented = false;
    }
}
