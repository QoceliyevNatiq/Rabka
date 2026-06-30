package com.Rabka.rabka.service;

import com.Rabka.rabka.dto.Restauran.RestaurantCreateDto;
import com.Rabka.rabka.dto.Restauran.RestaurantResponseDto;
import com.Rabka.rabka.dto.Restauran.RestaurantUpdateDto;
import com.Rabka.rabka.entity.RestaurantStatus;
import com.Rabka.rabka.entity.RestaurantType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;


public interface RestaurantService {
    RestaurantResponseDto createRestaurant(RestaurantCreateDto createDto);
    RestaurantResponseDto updateRestaurant(RestaurantUpdateDto updateDto);
    RestaurantResponseDto getRestaurantById(Long id);
    void deleteRestaurantById(Long id);
    Page<RestaurantResponseDto> getAllRestaurantsIsActive(Pageable pageable);
    Page<RestaurantResponseDto> getAllRestaurants(Pageable pageable);
    Page<RestaurantResponseDto> findRestaurantsByType(RestaurantType type, Pageable pageable);
    Page<RestaurantResponseDto> findRestaurantsByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<RestaurantResponseDto> findRestaurantsIsNotClosed(Pageable pageable, Time now);
    RestaurantResponseDto updateRestaurantStatus(Long id, RestaurantStatus status);


}
