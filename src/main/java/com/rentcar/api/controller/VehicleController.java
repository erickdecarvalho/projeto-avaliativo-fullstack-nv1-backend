package com.rentcar.api.controller;

import com.rentcar.api.dto.RegisterVehicleDTO;
import com.rentcar.api.dto.VehicleCreateResponseDTO;
import com.rentcar.api.model.Vehicle;
import com.rentcar.api.model.VehicleType;
import com.rentcar.api.repository.VehicleRepository;
import com.rentcar.api.service.VehicleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carros")
@CrossOrigin("*")
public class VehicleController {

    private VehicleService vehicleService;
    private VehicleRepository vehicleRepository;

    public VehicleController(VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VehicleCreateResponseDTO> registerVehicle(@RequestBody RegisterVehicleDTO registerVehicleDTO, UriComponentsBuilder uriBuilder) {
        VehicleCreateResponseDTO vehicleCreateResponseDTO = vehicleService.registerVehicle(registerVehicleDTO);
        URI location = uriBuilder.path("/vehicles/{id}").buildAndExpand(vehicleCreateResponseDTO.id()).toUri();
        return ResponseEntity.created(location).body(vehicleCreateResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(this.vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(this.vehicleService.getVehicleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable Long id) {
        this.vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/alugar/{id}")
    public ResponseEntity rentVehicle(@PathVariable Long id) {
        Vehicle vehicle = this.vehicleRepository.findById(id).get();

        if (vehicle.getIsRented()) {
            vehicle.setIsRented(false);
        }   else {
            vehicle.setIsRented(true);
        }

        vehicleRepository.save(vehicle);
        return ResponseEntity.ok().body(vehicle);
    }

    @GetMapping("/filtrar")
    public Page<Vehicle> getVehicles(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) VehicleType vehicleType,
            @RequestParam(required = false) String chassi,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Boolean isRented,
            Pageable pageable) {

        return vehicleService.findVehicles(name, vehicleType, chassi, year, color, isRented, pageable);
    }
}
