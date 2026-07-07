package com.Rabka.rabka.mapstruct;


import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import com.Rabka.rabka.entity.RestaurantMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMenuMapper {
    RestaurantMenu restaurantMenuResponseDtoToRestaurantMenu(RestaurantMenuResponseDto restaurantMenuResponseDto);
    RestaurantMenuResponseDto restaurantMenuToRestaurantMenuResponseDto(RestaurantMenu restaurantMenu);
    RestaurantMenu restaurantMenuCreateDtoToRestaurantMenu(RestaurantMenuCreateDto restaurantMenuCreateDto);
    RestaurantMenuResponseDto restaurantCreateToRestaurantMenuResponseDto(RestaurantMenuCreateDto restaurantMenuCreateDto);
}
