package com.rabka.restaurantservice.mapstruct;

import org.mapstruct.Mapper;

import com.rabka.restaurantservice.dto.Restauran.RestaurantCreateDto;
import com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto;
import com.rabka.restaurantservice.dto.Restauran.RestaurantUpdateDto;
import com.rabka.restaurantservice.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantResponseDto restaurantToRestaurantDto(Restaurant restaurant);
    Restaurant restaurantDtoToRestaurant(RestaurantResponseDto restaurantDto);
    Restaurant restaurantCreateDtoToRestaurant(RestaurantCreateDto restaurantCreateDto);
    Restaurant restaurantUpdateDtoToRestaurant(RestaurantUpdateDto restaurantUpdateDto);
}
