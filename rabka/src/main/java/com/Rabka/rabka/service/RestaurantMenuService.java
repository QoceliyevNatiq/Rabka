package com.Rabka.rabka.service;


import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import com.Rabka.rabka.entity.RestaurantMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface RestaurantMenuService {
    void deleteById(Long menuId);
    RestaurantMenuResponseDto create(RestaurantMenuCreateDto dto);
    RestaurantMenuResponseDto update(RestaurantMenuUpdateDto dto);
    RestaurantMenuResponseDto findById(Long id);
    Page<RestaurantMenuResponseDto> findAll(Pageable pageable);
}
