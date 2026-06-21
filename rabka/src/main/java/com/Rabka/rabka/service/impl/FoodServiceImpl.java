package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.config.UserMapper;
import com.Rabka.rabka.dto.food.FoodCreateDto;
import com.Rabka.rabka.dto.food.FoodResponseDto;
import com.Rabka.rabka.dto.food.FoodUpdateDto;
import com.Rabka.rabka.entity.Food;
import com.Rabka.rabka.exception.ResourceNotFoundException;
import com.Rabka.rabka.repo.FoodRepository;
import com.Rabka.rabka.service.FoodService;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final UserMapper userMapper;


    @Transactional
    @Override
    public FoodResponseDto createFood(FoodCreateDto food) {
        log.debug("createFood started | Category id; {} | FoodName; {}",food.CategoryId(),food.name());
        if (food.CategoryId() == null) {
            log.error("createFood food CategoryId is null");
            throw new ResourceNotFoundException("createFood","CategoryId", food.CategoryId());
        }
        Food foodEntity = new Food();
        userMapper.createDtoToFood(food);
        foodRepository.save(foodEntity);
        return userMapper.foodToFoodResponseDto(foodEntity);

    }

    @Override
    public FoodResponseDto updateFood(FoodUpdateDto foodUpdateDto) {
        return null;
    }

    @Override
    public void deleteFood(Long foodId) {

    }

    @Override
    public FoodResponseDto getFood(Long foodId) {
        return null;
    }

    @Override
    public Page<FoodResponseDto> findFoodByNameContainingIgnoreCase(String foodName, Pageable pageable) {
        return null;
    }

    @Override
    public Page<FoodResponseDto> findFoodByCategoryId(Long foodCategoryId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<FoodResponseDto> findFoodByCategoryName(String foodCategoryName, Pageable pageable) {
        return null;
    }

    @Override
    public Page<FoodResponseDto> findFoodByPrice(Double min, Double max, Pageable pageable) {
        return null;
    }

    @Override
    public Page<FoodResponseDto> getAllFood(Pageable pageable) {
        return null;
    }

    @Override
    public Page<FoodResponseDto> getAllFoodForAdmin(Pageable pageable) {
        return null;
    }
}
