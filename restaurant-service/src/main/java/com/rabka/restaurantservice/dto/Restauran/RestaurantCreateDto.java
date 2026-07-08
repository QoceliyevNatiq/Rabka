package com.rabka.restaurantservice.dto.Restauran;

import com.rabka.restaurantservice.entity.RestaurantBranch;
import com.rabka.restaurantservice.entity.RestaurantMenu;
import com.rabka.restaurantservice.entity.RestaurantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record RestaurantCreateDto(
        @NotBlank(message = "this part can't be empty")
        String name,

        @NotBlank(message ="this part can't be empty" )
        String logoUrl,

        @NotBlank(message = "this part can't be empty")
        String description,

        @NotNull(message = "this part can't be empty")
        RestaurantType type
) {
}
