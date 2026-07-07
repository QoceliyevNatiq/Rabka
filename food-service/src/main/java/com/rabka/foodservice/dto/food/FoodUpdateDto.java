package com.Rabka.rabka.dto.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record FoodUpdateDto(
        @NotNull
        Long id,
        @Size(max = 20)
        String name,

        @Size(max = 150)
        String description,

        BigDecimal price,

        @Size(max = 20)
        String portion,

        @Size(max = 255)
        String imageUrl,

        Long CategoryId,

        Boolean isActive
) {
}
