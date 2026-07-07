package com.Rabka.rabka.mapstruct;

import com.Rabka.rabka.dto.food.FoodCreateDto;
import com.Rabka.rabka.dto.food.FoodResponseDto;
import com.Rabka.rabka.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food createDtoToFood(FoodCreateDto dto);
    FoodCreateDto FoodToFoodCreateDto(Food food);
    FoodResponseDto foodToFoodResponseDto(Food food);
}
