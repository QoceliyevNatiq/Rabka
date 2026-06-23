package com.Rabka.rabka.dto.Restauran;

import com.Rabka.rabka.entity.RestaurantBranch;
import com.Rabka.rabka.entity.RestaurantMenu;
import com.Rabka.rabka.entity.RestaurantType;
import com.Rabka.rabka.entity.WorkingHours;

import java.util.List;

public record RestaurantResponseDto(
        String name,
        List<RestaurantBranch> branches,
        String logoUrl,
        String description,
        RestaurantMenu menu,
        Boolean active,
        RestaurantType type,
        WorkingHours workingHours


) {
}
