package com.Rabka.rabka.dto.food;

import java.math.BigDecimal;

public record FoodResponseDto(
        String name,
        String description,
        BigDecimal price,
        String portion,
        String imageUrl,
        Long categoryId
) {
}
