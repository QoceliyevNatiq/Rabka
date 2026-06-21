package com.Rabka.rabka.config;

import com.Rabka.rabka.dto.RegisterDto;
import com.Rabka.rabka.dto.food.FoodCreateDto;
import com.Rabka.rabka.dto.food.FoodResponseDto;
import com.Rabka.rabka.entity.Food;
import com.Rabka.rabka.entity.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterDto userToRegisterDto(User user);
    User registerDtoToUser(RegisterDto registerDto);
    Food createDtoToFood(FoodCreateDto dto);
    FoodCreateDto FoodToFoodCreateDto(Food food);
    FoodResponseDto foodToFoodResponseDto(Food food);
}
