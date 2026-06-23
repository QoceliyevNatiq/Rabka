package com.Rabka.rabka.dto.Restauran;

import com.Rabka.rabka.entity.RestaurantType;

public record RestaurantUpdateDto(
        Long id,
        String logoUrl,
        String name,
        String description,
        Boolean isActive,
        RestaurantType type,
        Long workingHoursId
) {
}
