package com.Rabka.rabka.dto.Restauran;

public record RestaurantUpdateDto(
        String logoUrl,
        String name,
        String description,
        Boolean isActive
) {
}
