package com.rabka.restaurantservice.dto.Restauran;

import com.rabka.restaurantservice.entity.RestaurantBranch;
import com.rabka.restaurantservice.entity.RestaurantMenu;
import com.rabka.restaurantservice.entity.RestaurantType;
import com.rabka.restaurantservice.entity.WorkingHours;

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
