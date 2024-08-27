package com.rentcar.api.dto;

public record RegisterCustomerDTO(
        String name,
        String email,
        String phoneNumber,
        String cpf
) {
}
