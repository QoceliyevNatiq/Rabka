package com.rabka.foodservice.controller;


import com.rabka.foodservice.dto.food.FoodCreateDto;
import com.rabka.foodservice.dto.food.FoodResponseDto;
import com.rabka.foodservice.dto.food.FoodUpdateDto;
import com.rabka.foodservice.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
@Slf4j
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<FoodResponseDto> createFood(@RequestBody @Valid FoodCreateDto foodCreateDto){
        log.debug("Rest request to create food | name: {}", foodCreateDto.name());
        FoodResponseDto foodResponseDto = foodService.createFood(foodCreateDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(foodResponseDto.id()).toUri();
        log.info("Rest request to create food | id: {}", foodResponseDto.id());
        return ResponseEntity.created(location).body(foodResponseDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<FoodResponseDto> updateFood(@RequestBody @Valid FoodUpdateDto foodUpdateDto){
        log.debug("Rest request to update food | id: {}", foodUpdateDto.id());
        FoodResponseDto foodResponseDto = foodService.updateFood(foodUpdateDto);
        log.info("Rest request to update food | id: {}", foodResponseDto.id());
        return ResponseEntity.ok().body(foodResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id){
        log.debug("Rest request to delete food | id: {}", id);
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activation/{id}")
    public void setIsActive(@PathVariable  Long id, @RequestParam Boolean setting){
        log.debug("Rest request to activate food | id: {}", id);
        foodService.setActive(id, setting);
        log.info("Rest request to activate food | id: {}", id);
    }

    @GetMapping("{id}")
    public ResponseEntity<FoodResponseDto> getFood(@PathVariable Long id){
        log.debug("Rest request to get food | id: {}", id);
        FoodResponseDto dto =  foodService.getFoodById(id);
        log.info("Rest request to get food | id: {}", id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/searching")
    public ResponseEntity<Page<FoodResponseDto>> getAllFoodByName(@RequestParam String name, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        log.debug("Rest request to get food | name: {}", name);
        Page<FoodResponseDto> foodResponseDtos = foodService.findFoodByNameContainingIgnoreCase(name, pageable);
        log.info("Rest request to get food | totalElements: {}", foodResponseDtos.getTotalElements());
        return ResponseEntity.ok(foodResponseDtos);
    }

    @GetMapping("/searching/categoryId/{id}")
    public ResponseEntity<Page<FoodResponseDto>> getAllFoodByCategoryId(@PathVariable  Long id, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
            {
        log.debug("Rest request to get food | id: {}", id);
        Page<FoodResponseDto> foodResponseDtos = foodService.findFoodByCategoryId(id, pageable);
        log.info("Rest request to get food | totalElements: {}", foodResponseDtos.getTotalElements());
       return ResponseEntity.ok(foodResponseDtos);
    }

    @GetMapping("/searching/categoryName")
    public ResponseEntity<Page<FoodResponseDto>> getAllFoodByCategoryName(@RequestParam  String categoryName, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        log.debug("Rest request to get food by category name | categoryName: {}", categoryName);
        Page<FoodResponseDto> foodResponseDtos = foodService.findFoodByCategoryName(categoryName, pageable);
        log.info("Rest request to get food by category name | totalElements: {}", foodResponseDtos.getTotalElements());
        return ResponseEntity.ok(foodResponseDtos);
    }

    @GetMapping("/searching/price")
    public ResponseEntity<Page<FoodResponseDto>> getAllFoodByPrice(@RequestParam Double min, @RequestParam Double max, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        log.debug("Rest request to get food by price | min: {} max: {}", min, max);
        Page<FoodResponseDto> foodResponseDtos = foodService.findFoodByPrice(min, max, pageable);
        log.info("Rest request to get food by price | totalElements: {}", foodResponseDtos.getTotalElements());
        return ResponseEntity.ok(foodResponseDtos);
    }

    @GetMapping
    public ResponseEntity<Page<FoodResponseDto>> getAllFood(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        log.debug("Rest request to get all food");
        Page<FoodResponseDto> foodResponseDtos = foodService.getAllFood(pageable);
        log.info("Rest request to get all food | totalElements: {}", foodResponseDtos.getTotalElements());
        return ResponseEntity.ok(foodResponseDtos);
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<FoodResponseDto>> getAllFoodForAdmin(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        log.debug("Rest request to get all food for admin");
        Page<FoodResponseDto> foodResponseDtos = foodService.getAllFoodForAdmin(pageable);
        log.info("Rest request to get all food for admin | totalElements: {}", foodResponseDtos.getTotalElements());
        return ResponseEntity.ok(foodResponseDtos);
    }
}
