package com.rabka.foodservice.dto.MenuCategory;



import java.util.List;

public record MenuCategoryResponseDto(
        String name,
        String description,
        List<Long> foodIdList
) {
}
