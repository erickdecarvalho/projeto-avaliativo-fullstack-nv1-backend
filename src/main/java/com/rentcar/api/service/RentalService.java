package com.rentcar.api.service;

import com.rentcar.api.dto.RegisterRentalDTO;
import com.rentcar.api.dto.RentalCreateResponseDTO;
import com.rentcar.api.mapper.CustomerMapper;
import com.rentcar.api.mapper.RentalMapper;
import com.rentcar.api.model.Customer;
import com.rentcar.api.model.Rental;
import com.rentcar.api.repository.CustomerRepository;
import com.rentcar.api.repository.RentalRepository;
import com.rentcar.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    private CustomerRepository customerRepository;
    private VehicleRepository vehicleRepository;
    private RentalRepository rentalRepository;

    public RentalService(CustomerRepository customerRepository, VehicleRepository vehicleRepository, RentalRepository rentalRepository) {
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.rentalRepository = rentalRepository;
    }

    public RentalCreateResponseDTO registerRental(RegisterRentalDTO registerRentalDTO) {
        Rental rental = RentalMapper.registerRentalDTOToRental(registerRentalDTO, customerRepository, vehicleRepository);
        rental.getVehicle().setIsRented(true);
        Rental savedRental = rentalRepository.save(rental);

        return RentalMapper.rentalToRentalCreateResponseDTO(savedRental);
    }
}
