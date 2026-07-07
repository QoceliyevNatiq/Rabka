package com.Rabka.rabka.dto.RestaurantMenu;

import jakarta.validation.constraints.NotNull;

public record RestaurantMenuUpdateDto(
        @NotNull
        Long id,
        String name



) {
}
