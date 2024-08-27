package com.rentcar.api.dto;

public record CustomerCreateResponseDTO(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String cpf
) {
}
