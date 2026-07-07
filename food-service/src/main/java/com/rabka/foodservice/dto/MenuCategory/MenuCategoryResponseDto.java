package com.Rabka.rabka.dto.MenuCategory;

import com.Rabka.rabka.dto.food.FoodResponseDto;

import java.util.List;

public record MenuCategoryResponseDto(
        String name,
        String description,
        List<Long> foodIdList
) {
}
