package com.Rabka.rabka.dto.RestaurantMenu;

import com.Rabka.rabka.dto.MenuCategory.MenuCategoryResponseDto;

import java.util.List;

public record RestaurantMenuResponseDto(
        Long id,
        String name,
        List<MenuCategoryResponseDto> categoryResponseDto,
        Long restaurantId
) {
}
