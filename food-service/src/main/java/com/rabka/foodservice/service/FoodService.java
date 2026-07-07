package com.rabka.foodservice.service;


import com.rabka.foodservice.dto.food.FoodCreateDto;
import com.rabka.foodservice.dto.food.FoodResponseDto;
import com.rabka.foodservice.dto.food.FoodUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
