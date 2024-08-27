package com.rentcar.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RegisterRentalDTO(
        Long customerId,
        Long vehicleId,
        BigDecimal price,
        LocalDateTime startDate,
        LocalDateTime finalDate
) {
}
