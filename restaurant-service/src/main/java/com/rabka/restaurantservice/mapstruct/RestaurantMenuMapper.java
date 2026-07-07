package com.rabka.restaurantservice.mapstruct;


import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import com.rabka.restaurantservice.entity.RestaurantMenu;

@Mapper(componentModel = "spring")
public interface RestaurantMenuMapper {
    RestaurantMenu restaurantMenuResponseDtoToRestaurantMenu(RestaurantMenuResponseDto restaurantMenuResponseDto);
    RestaurantMenuResponseDto restaurantMenuToRestaurantMenuResponseDto(RestaurantMenu restaurantMenu);
    RestaurantMenu restaurantMenuCreateDtoToRestaurantMenu(RestaurantMenuCreateDto restaurantMenuCreateDto);
    RestaurantMenuResponseDto restaurantCreateToRestaurantMenuResponseDto(RestaurantMenuCreateDto restaurantMenuCreateDto);
}
