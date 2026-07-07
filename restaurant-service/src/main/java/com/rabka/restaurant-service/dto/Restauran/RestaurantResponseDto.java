package com.Rabka.rabka.dto.Restauran;

import com.Rabka.rabka.entity.RestaurantBranch;
import com.Rabka.rabka.entity.RestaurantMenu;
import com.Rabka.rabka.entity.RestaurantType;
import com.Rabka.rabka.entity.WorkingHours;

import java.util.List;

public record RestaurantResponseDto(
        Long id,
        String name,
        List<RestaurantBranch> branches,
        String logoUrl,
        String description,
        RestaurantMenu menu,
        Boolean active,
        RestaurantType type
//        List<WorkingHours> workingHours
        //change the entities with dtos


) {
}
