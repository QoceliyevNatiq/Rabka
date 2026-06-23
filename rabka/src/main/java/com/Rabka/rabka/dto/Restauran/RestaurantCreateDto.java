package com.Rabka.rabka.dto.Restauran;

import com.Rabka.rabka.entity.RestaurantBranch;
import com.Rabka.rabka.entity.RestaurantMenu;
import com.Rabka.rabka.entity.RestaurantType;

import java.util.List;

public record RestaurantCreateDto(
        String name,
        String logoUrl,
        String description,
        RestaurantType type
) {
}
