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
            throw new ResourceNotFoundException("createFood","CategoryId", food.categoryId());
        }
        Food foodEntity = userMapper.createDtoToFood(food);
        foodRepository.save(foodEntity);
        return userMapper.foodToFoodResponseDto(foodEntity);

    }

    @Override
    public FoodResponseDto updateFood(FoodUpdateDto foodUpdateDto) {
        log.debug("updateFood started | Category id; {} | Food name: {}",foodUpdateDto.CategoryId(),foodUpdateDto.name());
        if (foodUpdateDto.CategoryId() == null) {
            log.error("updateFood food CategoryId is null");
            throw new ResourceNotFoundException("updateFood","CategoryId", foodUpdateDto.CategoryId());
        }

        return null;
    }
    @Transactional
    @Override
    public void deleteFood(Long foodId) {
        log.debug("deleteFood started | Category id; {}",foodId);
        if (foodId == null) {
            log.error("deleteFood food foodId is null");
            throw new ResourceNotFoundException("deleteFood","foodId", foodId);
        }
        foodRepository.deleteById(foodId);
        log.debug("deleteFood successfully ended foodId {}",foodId);
    }

    @Override
    @Transactional
    public void setActive(Long foodId, Boolean active) {
        log.debug("setActive started | Food id; {}",foodId);
        Food food = foodRepository.findById(foodId)

                .orElseThrow(() -> {log.error("setActive food food foodId {} not found",foodId);
                    return new ResourceNotFoundException("food", "foodId", foodId);
                });
        food.setIsActive(active);
        foodRepository.save(food);
        log.debug("setActive successfully ended foodId {}",foodId);
    }

    @Override
    public FoodResponseDto getFoodById(Long foodId) {
        log.debug("getFoodById started | Food id; {}",foodId);
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> {
                    log.error("getFoodById food foodId {} not found",foodId);
                    return new ResourceNotFoundException("food", "foodId", foodId);
                });
        log.debug("getFoodById successfully ended foodId {}",foodId);
        return userMapper.foodToFoodResponseDto(food);

    }

    @Override
    public Page<FoodResponseDto> findFoodByNameContainingIgnoreCase(String foodName, Pageable pageable) {
        log.debug("findFoodByNameContainingIgnoreCase started | foodName; {}",foodName);
        Page<FoodResponseDto> foods = foodRepository.findFoodByNameContainingIgnoreCase(foodName,pageable)
                .map(userMapper::foodToFoodResponseDto);
        log.debug("findFoodByNameContainingIgnoreCase successfully ended | foodList: {}",foods.getTotalElements());
        return foods;
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
