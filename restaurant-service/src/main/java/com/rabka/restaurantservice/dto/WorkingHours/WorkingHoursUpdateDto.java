package com.rabka.restaurantservice.dto.WorkingHours;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingHoursUpdateDto(
        @NotNull
        Long id,
        LocalTime openingTime,
        LocalTime closingTime,
        DayOfWeek dayOfWeek
) {
}
