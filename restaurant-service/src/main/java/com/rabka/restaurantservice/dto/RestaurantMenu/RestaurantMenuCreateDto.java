package com.Rabka.rabka.dto.RestaurantMenu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public record RestaurantMenuCreateDto(
        @NotBlank(message = "Menu name can't be empty")
        @Size(max =50)
        String name,

        @NotNull(message = "menu should belong to restaurant")
        Long restaurantId


) {
}
