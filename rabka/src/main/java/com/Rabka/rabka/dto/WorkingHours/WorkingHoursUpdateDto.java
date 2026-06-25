package com.Rabka.rabka.dto.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record WorkingHoursUpdateDto(
        LocalTime openingTime,
        LocalTime closingTime,
        DayOfWeek dayOfWeek
) {
}
