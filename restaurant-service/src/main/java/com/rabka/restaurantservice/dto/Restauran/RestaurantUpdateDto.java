package com.rabka.restaurantservice.dto.Restauran;

import com.rabka.restaurantservice.entity.RestaurantType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RestaurantUpdateDto(
        @NotNull
        Long id,

        @Size(max = 255)
        String logoUrl,

        @Size(max = 20)
        String name,

        @Size(max = 255)
        String description,
        Boolean isActive,
        RestaurantType type
) {
}
