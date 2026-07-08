package com.rabka.restaurantservice.dto.WorkingHours;

import java.time.LocalTime;

public record WorkingHoursResponseDto(
        Long id,
        Long restaurantBranchId,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
