package com.rentcar.api.controller;

import com.rentcar.api.dto.CustomerCreateResponseDTO;
import com.rentcar.api.dto.RegisterCustomerDTO;
import com.rentcar.api.dto.RegisterRentalDTO;
import com.rentcar.api.dto.RentalCreateResponseDTO;
import com.rentcar.api.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/locacoes")
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RentalCreateResponseDTO> registerRental(@RequestBody RegisterRentalDTO registerRentalDTO, UriComponentsBuilder uriBuilder) {
        RentalCreateResponseDTO rentalCreateResponseDTO = rentalService.registerRental(registerRentalDTO);
        URI location = uriBuilder.path("/clientes/{id}").buildAndExpand(rentalCreateResponseDTO.id()).toUri();
        return ResponseEntity.created(location).body(rentalCreateResponseDTO);
    }
}
