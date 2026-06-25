package com.Rabka.rabka.dto.WorkingHours;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingHoursCreateDto(
        @NotNull(message = "this part can't be empty")
        Long restaurantId,

        @NotNull(message = "this part can't be empty")
        LocalTime openingTime,
        
        @NotNull(message = "this part can't be empty")
        LocalTime closingTime,

        @NotNull(message = "this part can't be empty")
        DayOfWeek dayOfWeek
) {
}
