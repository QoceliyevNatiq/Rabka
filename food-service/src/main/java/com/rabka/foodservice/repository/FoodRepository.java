package com.Rabka.rabka.repo;

import com.Rabka.rabka.dto.food.FoodResponseDto;
import com.Rabka.rabka.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findFoodByNameContainingIgnoreCase(String foodName, Pageable pageable);
    Page<Food> findFoodByCategoryId(Long id, Pageable pageable);
    Page<Food> findFoodByCategoryName(String foodCategoryName, Pageable pageable);
    Page<Food> findFoodByPriceBetween(Double min, Double max, Pageable pageable);
    Page<Food> findAllFoodIsActive(Pageable pageable);
    Food findFoodById(Long id);
}
