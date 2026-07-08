package com.rabka.restaurantservice.service;


import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RestaurantMenuService {
    void deleteById(Long menuId);
    RestaurantMenuResponseDto create(RestaurantMenuCreateDto dto);
    RestaurantMenuResponseDto update(RestaurantMenuUpdateDto dto);
    RestaurantMenuResponseDto findById(Long id);
    Page<RestaurantMenuResponseDto> findAll(Pageable pageable);
}
