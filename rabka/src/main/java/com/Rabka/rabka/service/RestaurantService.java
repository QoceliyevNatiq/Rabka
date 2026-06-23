package com.Rabka.rabka.service;

import com.Rabka.rabka.dto.Restauran.RestaurantCreateDto;
import com.Rabka.rabka.dto.Restauran.RestaurantResponseDto;
import com.Rabka.rabka.dto.Restauran.RestaurantUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {
    RestaurantResponseDto createRestaurant(RestaurantCreateDto createDto);
    RestaurantResponseDto updateRestaurant(RestaurantUpdateDto updateDto);
    RestaurantResponseDto getRestaurantById(Long id);

}
