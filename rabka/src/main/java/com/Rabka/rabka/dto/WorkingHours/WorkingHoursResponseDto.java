package com.Rabka.rabka.dto.WorkingHours;

import java.time.LocalTime;

public record WorkingHoursResponseDto(
        Long id,
        Long restaurantId,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
