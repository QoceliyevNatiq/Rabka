package com.Rabka.rabka.service;

import com.Rabka.rabka.dto.food.FoodCreateDto;
import com.Rabka.rabka.dto.food.FoodResponseDto;
import com.Rabka.rabka.dto.food.FoodUpdateDto;
import com.Rabka.rabka.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FoodService {
    FoodResponseDto createFood(FoodCreateDto food);
    FoodResponseDto updateFood(FoodUpdateDto foodUpdateDto);
    void  deleteFood(Long foodId);
    void setActive(Long foodId,Boolean active);
    FoodResponseDto getFoodById(Long foodId);
    Page<FoodResponseDto> findFoodByNameContainingIgnoreCase(String foodName,Pageable pageable);
    Page<FoodResponseDto> findFoodByCategoryId(Long foodCategoryId, Pageable pageable);
    Page<FoodResponseDto> findFoodByCategoryName(String foodCategoryName, Pageable pageable);
    Page<FoodResponseDto> findFoodByPrice(Double min, Double max, Pageable pageable);
    Page<FoodResponseDto> getAllFood(Pageable pageable);
    Page<FoodResponseDto> getAllFoodForAdmin(Pageable pageable);

}
