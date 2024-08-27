package com.rentcar.api.controller;

import com.rentcar.api.dto.CustomerCreateResponseDTO;
import com.rentcar.api.dto.RegisterCustomerDTO;
import com.rentcar.api.dto.RegisterVehicleDTO;
import com.rentcar.api.dto.VehicleCreateResponseDTO;
import com.rentcar.api.model.Customer;
import com.rentcar.api.model.Vehicle;
import com.rentcar.api.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerCreateResponseDTO> registerCustomer(@RequestBody RegisterCustomerDTO registerCustomerDTO, UriComponentsBuilder uriBuilder) {
        CustomerCreateResponseDTO customerCreateResponseDTO = customerService.registerCustomer(registerCustomerDTO);
        URI location = uriBuilder.path("/clientes/{id}").buildAndExpand(customerCreateResponseDTO.id()).toUri();
        return ResponseEntity.created(location).body(customerCreateResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(this.customerService.getCustomerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
