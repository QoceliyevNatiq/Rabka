package com.rabka.restaurantservice.dto.RestaurantMenu;

import java.util.List;

public record RestaurantMenuResponseDto(
        Long id,
        String name,
        Long restaurantId
) {
}
