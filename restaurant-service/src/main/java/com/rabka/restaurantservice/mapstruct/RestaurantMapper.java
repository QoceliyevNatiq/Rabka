package com.rabka.restaurantservice.mapstruct;


import com.Rabka.rabka.dto.Restauran.RestaurantCreateDto;
import com.Rabka.rabka.dto.Restauran.RestaurantResponseDto;
import com.Rabka.rabka.dto.Restauran.RestaurantUpdateDto;
import com.rabka.restaurantservice.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantResponseDto restaurantToRestaurantDto(Restaurant restaurant);
    Restaurant restaurantDtoToRestaurant(RestaurantResponseDto restaurantDto);
    Restaurant restaurantCreateDtoToRestaurant(RestaurantCreateDto restaurantCreateDto);
    Restaurant restaurantUpdateDtoToRestaurant(RestaurantUpdateDto restaurantUpdateDto);
}
