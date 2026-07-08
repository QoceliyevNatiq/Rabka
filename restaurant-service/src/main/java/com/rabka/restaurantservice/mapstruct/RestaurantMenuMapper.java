package com.rabka.restaurantservice.mapstruct;

import org.mapstruct.Mapper;

import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import com.rabka.restaurantservice.entity.RestaurantMenu;

@Mapper(componentModel = "spring")
public interface RestaurantMenuMapper {
    RestaurantMenu restaurantMenuResponseDtoToRestaurantMenu(RestaurantMenuResponseDto restaurantMenuResponseDto);
    RestaurantMenuResponseDto restaurantMenuToRestaurantMenuResponseDto(RestaurantMenu restaurantMenu);
    RestaurantMenu restaurantMenuCreateDtoToRestaurantMenu(RestaurantMenuCreateDto restaurantMenuCreateDto);
    RestaurantMenuResponseDto restaurantCreateToRestaurantMenuResponseDto(RestaurantMenuCreateDto restaurantMenuCreateDto);
}
