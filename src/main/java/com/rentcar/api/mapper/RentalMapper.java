package com.rentcar.api.mapper;

import com.rentcar.api.dto.RegisterRentalDTO;
import com.rentcar.api.dto.RegisterVehicleDTO;
import com.rentcar.api.dto.RentalCreateResponseDTO;
import com.rentcar.api.dto.VehicleCreateResponseDTO;
import com.rentcar.api.model.Customer;
import com.rentcar.api.model.Rental;
import com.rentcar.api.model.Vehicle;
import com.rentcar.api.repository.CustomerRepository;
import com.rentcar.api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RentalMapper {

    public static Rental registerRentalDTOToRental(RegisterRentalDTO dto, CustomerRepository customerRepository,
                                                   VehicleRepository vehicleRepository) {
        Customer customer = customerRepository.findById(dto.customerId()).get();
        Vehicle vehicle = vehicleRepository.findById(dto.vehicleId()).get();

        return Rental.builder()
                .customer(customer)
                .vehicle(vehicle)
                .price(dto.price())
                .startDate(dto.startDate())
                .finalDate(dto.finalDate())
                .build();
    }

    public static RentalCreateResponseDTO rentalToRentalCreateResponseDTO(Rental rental) {
        if (rental == null) {
            return null;
        }

        return new RentalCreateResponseDTO(
                rental.getId(),
                rental.getCustomer(),
                rental.getVehicle(),
                rental.getPrice(),
                rental.getStartDate(),
                rental.getFinalDate()
        );
    }
}
