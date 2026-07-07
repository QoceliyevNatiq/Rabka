package com.rabka.foodservice.mapsturct;


import com.rabka.foodservice.dto.food.FoodCreateDto;
import com.rabka.foodservice.dto.food.FoodResponseDto;
import com.rabka.foodservice.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food createDtoToFood(FoodCreateDto dto);
    FoodCreateDto FoodToFoodCreateDto(Food food);
    FoodResponseDto foodToFoodResponseDto(Food food);
}
