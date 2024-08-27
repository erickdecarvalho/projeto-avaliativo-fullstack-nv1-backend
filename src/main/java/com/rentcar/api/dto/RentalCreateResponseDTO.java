package com.rentcar.api.dto;

import com.rentcar.api.model.Customer;
import com.rentcar.api.model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RentalCreateResponseDTO(
        Long id,
        Customer customer,
        Vehicle vehicle,
        BigDecimal price,
        LocalDateTime startDate,
        LocalDateTime finalDate
) {
}
