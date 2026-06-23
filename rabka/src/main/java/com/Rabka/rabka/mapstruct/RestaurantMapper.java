package com.Rabka.rabka.mapstruct;


import com.Rabka.rabka.dto.Restauran.RestaurantCreateDto;
import com.Rabka.rabka.dto.Restauran.RestaurantResponseDto;
import com.Rabka.rabka.dto.Restauran.RestaurantUpdateDto;
import com.Rabka.rabka.entity.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantResponseDto restaurantToRestaurantDto(Restaurant restaurant);
    Restaurant restaurantDtoToRestaurant(RestaurantResponseDto restaurantDto);
    Restaurant restaurantCreateDtoToRestaurant(RestaurantCreateDto restaurantCreateDto);
    Restaurant restaurantUpdateDtoToRestaurant(RestaurantUpdateDto restaurantUpdateDto);
}
