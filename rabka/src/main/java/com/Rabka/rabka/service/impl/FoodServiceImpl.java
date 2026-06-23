package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.config.UserMapper;
import com.Rabka.rabka.dto.food.FoodCreateDto;
import com.Rabka.rabka.dto.food.FoodResponseDto;
import com.Rabka.rabka.dto.food.FoodUpdateDto;
import com.Rabka.rabka.entity.Food;
import com.Rabka.rabka.entity.MenuCategory;
import com.Rabka.rabka.exception.ResourceNotFoundException;
import com.Rabka.rabka.repo.FoodRepository;
import com.Rabka.rabka.repo.MenuCategoryRepository;
import com.Rabka.rabka.service.FoodService;
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
    private final MenuCategoryRepository categoryRepository;


    @Transactional
    @Override
    public FoodResponseDto createFood(FoodCreateDto food) {
        log.debug("createFood started | Category id; {} | FoodName; {}",food.categoryId(),food.name());
        MenuCategory category = categoryRepository.findById(food.categoryId())
                        .orElseThrow(() -> {
            log.warn("createFood food CategoryId is null");
            return new ResourceNotFoundException("createFood","CategoryId", food.categoryId());
        });
        Food foodEntity = userMapper.createDtoToFood(food);
        foodRepository.save(foodEntity);
        log.info("createFood finished | Food id {}", foodEntity.getId());
        return userMapper.foodToFoodResponseDto(foodEntity);

    }

    @Override
    public FoodResponseDto updateFood(FoodUpdateDto foodUpdateDto) {
        log.debug("updateFood started | Category id; {} | Food name: {}",foodUpdateDto.CategoryId(),foodUpdateDto.name());
        Food food =  foodRepository.findById(foodUpdateDto.id())
                        .orElseThrow(() -> {
            log.warn("updateFood food CategoryId is null");
            return new ResourceNotFoundException("updateFood","FoodId", null);
        });
        if(foodUpdateDto.name() != null) {
            food.setName(foodUpdateDto.name());
        }
        if(foodUpdateDto.description() != null) {
            food.setDescription(foodUpdateDto.description());
        }
        if(foodUpdateDto.price() != null) {
            food.setPrice(foodUpdateDto.price());
        }
        if(foodUpdateDto.portion() != null) {
            food.setPortion(foodUpdateDto.portion());
        }
        if(foodUpdateDto.imageUrl() != null) {
            food.setImageUrl(foodUpdateDto.imageUrl());
        }
        if(foodUpdateDto.CategoryId() != null) {
            food.setCategoryId(foodUpdateDto.CategoryId());
        }
        if(foodUpdateDto.isActive()!=null) {
            food.setIsActive(foodUpdateDto.isActive());
        }
        log.info("updateFood finished | Food id {}", food.getId());
        return userMapper.foodToFoodResponseDto(foodRepository.save(food));


    }
    @Transactional
    @Override
    public void deleteFood(Long foodId) {
        log.debug("deleteFood started | Category id; {}",foodId);
        if (foodRepository.findFoodById(foodId) == null) {
            log.warn("deleteFood food foodId is null");
            throw new ResourceNotFoundException("deleteFood","foodId", foodId);
        }
        foodRepository.deleteById(foodId);
        log.info("deleteFood successfully ended foodId {}",foodId);
    }

    @Override
    @Transactional
    public void setActive(Long foodId, Boolean active) {
        log.debug("setActive started | Food id; {}",foodId);
        Food food = foodRepository.findById(foodId)

                .orElseThrow(() -> {log.warn("setActive food food foodId {} not found",foodId);
                    return new ResourceNotFoundException("food", "foodId", foodId);
                });
        food.setIsActive(active);
        foodRepository.save(food);
        log.info("setActive successfully ended foodId {}",foodId);
    }

    @Override
    public FoodResponseDto getFoodById(Long foodId) {
        log.debug("getFoodById started | Food id; {}",foodId);
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> {
                    log.error("getFoodById food foodId {} not found",foodId);
                    return new ResourceNotFoundException("food", "foodId", foodId);
                });
        log.info("getFoodById successfully ended foodId {}",foodId);
        return userMapper.foodToFoodResponseDto(food);

    }

    @Override
    public Page<FoodResponseDto> findFoodByNameContainingIgnoreCase(String foodName, Pageable pageable) {
        log.debug("findFoodByNameContainingIgnoreCase started | foodName; {}",foodName);
        Page<FoodResponseDto> foods = foodRepository.findFoodByNameContainingIgnoreCase(foodName,pageable)
                .map(userMapper::foodToFoodResponseDto);
        log.info("findFoodByNameContainingIgnoreCase successfully ended | foodList: {}",foods.getTotalElements());
        return foods;
    }

    @Override
    public Page<FoodResponseDto> findFoodByCategoryId(Long foodCategoryId, Pageable pageable) {
        log.debug("findFoodByCategoryId started | foodCategoryId; {}",foodCategoryId);
        Page<FoodResponseDto> foods = foodRepository.findFoodByCategoryId(foodCategoryId,pageable)
                .map(userMapper::foodToFoodResponseDto);
        log.info("findFoodByCategoryId ended | foodList: {}",foods.getTotalElements());
        return foods;
    }

    @Override
    public Page<FoodResponseDto> findFoodByCategoryName(String foodCategoryName, Pageable pageable) {
        log.debug("findFoodByCategoryName started | foodCategoryName; {}",foodCategoryName);
        Page<FoodResponseDto> foods = foodRepository.findFoodByCategoryName(foodCategoryName,pageable)
                .map(userMapper::foodToFoodResponseDto);
        log.info("findFoodByCategoryName ended | foodList: {}",foods.getTotalElements());
        return foods;
    }

    @Override
    public Page<FoodResponseDto> findFoodByPrice(Double min, Double max, Pageable pageable) {
        log.debug("foodByPrice started | min; {}, max; {}",min,max);
        Page<FoodResponseDto> foods = foodRepository.findFoodByPriceBetween(min,max,pageable)
                .map(userMapper::foodToFoodResponseDto);
        log.info("foodByPrice ended | foodList: {}",foods.getTotalElements());
        return foods;
    }

    @Override
    public Page<FoodResponseDto> getAllFoodForAdmin(Pageable pageable) {
        log.debug("getAllFood started");
        Page<FoodResponseDto> foods = foodRepository.findAll(pageable).map(userMapper::foodToFoodResponseDto);
        log.info("getAllFood ended | foodList: {}",foods.getTotalElements());
        return foods;
    }

    @Override
    public Page<FoodResponseDto> getAllFood(Pageable pageable) {
        log.debug("getAllFoodForAdmin started | pageable; {}",pageable);
        Page<FoodResponseDto> foods = foodRepository.findAllFoodIsActive(pageable).map(userMapper::foodToFoodResponseDto);
        log.info("getAllFoodForAdmin ended | foodList: {}",foods.getTotalElements());
        return foods;
    }
}
