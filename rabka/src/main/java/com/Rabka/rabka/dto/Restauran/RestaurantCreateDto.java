package com.Rabka.rabka.dto.Restauran;

import com.Rabka.rabka.entity.RestaurantBranch;
import com.Rabka.rabka.entity.RestaurantMenu;
import com.Rabka.rabka.entity.RestaurantType;
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
