package com.Rabka.rabka.dto.WorkingHours;

import java.time.LocalTime;

public record WorkingHoursResponseDto(
        Long id,
        Long restaurantBranchId,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
