package com.rentcar.api.mapper;

import com.rentcar.api.dto.CustomerCreateResponseDTO;
import com.rentcar.api.dto.RegisterCustomerDTO;
import com.rentcar.api.model.Customer;

public class CustomerMapper {
    public static Customer registerCustomerDTOToCustomer(RegisterCustomerDTO dto) {
        return Customer.builder()
                .name(dto.name())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .cpf(dto.cpf())
                .build();
    }

    public static CustomerCreateResponseDTO customerToCustomerCreateResponseDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerCreateResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCpf()
        );
    }
}
