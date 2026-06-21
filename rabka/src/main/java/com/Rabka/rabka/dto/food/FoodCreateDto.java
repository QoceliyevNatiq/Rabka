package com.Rabka.rabka.dto.food;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record FoodCreateDto(
        @NotBlank(message = "name can't be empty")
        @Size(max = 20)
        String name,
        @NotBlank(message = "descrioption can't be empty")
        @Size(max = 150)
        String description,

        @NotNull(message = "price can't be empty")
        BigDecimal price,

        @NotBlank(message = "portion can't be empty")
        @Size(max = 20)
        String portion,

        @NotBlank(message = "image can't be empty")
        @Size(max = 255)
        String imageUrl,

        @NotNull(message = "food should belong to category")
        Long categoryId
) {
}
