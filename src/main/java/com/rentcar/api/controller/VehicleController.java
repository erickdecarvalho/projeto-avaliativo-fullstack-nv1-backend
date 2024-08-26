package com.rentcar.api.controller;

import com.rentcar.api.dto.RegisterVehicleDTO;
import com.rentcar.api.dto.VehicleCreateResponseDTO;
import com.rentcar.api.model.Vehicle;
import com.rentcar.api.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
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
}
