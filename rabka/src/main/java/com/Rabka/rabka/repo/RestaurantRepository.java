package com.Rabka.rabka.repo;

import com.Rabka.rabka.dto.Restauran.RestaurantResponseDto;
import com.Rabka.rabka.entity.Restaurant;
import com.Rabka.rabka.entity.RestaurantType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Restaurant> findAllIsActive(Pageable pageable);

    Page<Restaurant> findRestaurantByType(RestaurantType type,
                                                     Pageable pageable);

    List<Restaurant> type(RestaurantType type);

    Page<Restaurant> findRestaurantsByNameContainingIgnoreCase(String name,
                                                               Pageable pageable);
}
