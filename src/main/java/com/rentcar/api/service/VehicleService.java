package com.rentcar.api.service;

import com.rentcar.api.dto.RegisterVehicleDTO;
import com.rentcar.api.dto.VehicleCreateResponseDTO;
import com.rentcar.api.mapper.VehicleMapper;
import com.rentcar.api.model.Vehicle;
import com.rentcar.api.model.VehicleType;
import com.rentcar.api.repository.VehicleRepository;
import com.rentcar.api.specification.VehicleSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleCreateResponseDTO registerVehicle(RegisterVehicleDTO registerVehicleDTO) {
        Vehicle vehicle = VehicleMapper.registerVehicleDTOToVehicle(registerVehicleDTO);
        vehicle.setIsRented(false);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.vehicleToVehicleCreateResponseDTO(savedVehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return this.vehicleRepository.findById(id).get();
    }

    public void deleteVehicle(Long id) {
        this.vehicleRepository.deleteById(id);
    }

    public Page<Vehicle> findVehicles(String name, VehicleType vehicleType, String chassi, Integer year, String color, Boolean isRented, Pageable pageable) {
        Specification<Vehicle> spec = Specification.where(VehicleSpecification.nameContains(name))
                .and(VehicleSpecification.vehicleTypeEquals(vehicleType))
                .and(VehicleSpecification.yearEquals(year))
                .and(VehicleSpecification.chassiEquals(chassi))
                .and(VehicleSpecification.colorEquals(color))
                .and(VehicleSpecification.isRentedEquals(isRented));

        return vehicleRepository.findAll(spec, pageable);
    }
}
