package com.Rabka.rabka.repo;


import com.Rabka.rabka.entity.Restaurant;
import com.Rabka.rabka.entity.RestaurantType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Restaurant> findByIsActiveTrue(Pageable pageable);

    Page<Restaurant> findRestaurantByType(RestaurantType type,
                                                     Pageable pageable);

    List<Restaurant> type(RestaurantType type);

    Page<Restaurant> findRestaurantsByNameContainingIgnoreCase(String name,
                                                               Pageable pageable);
    @Query("""
         SELECT DISTINCT r FROM Restaurant r                                                                                                                                                                                                                        
         JOIN r.workingHours wh                                                                                                                                                                                                                                     
         WHERE r.isActive = true                                                                                                                                                                                                                                    
         AND r.status <> 'CLOSED'                                                                                                                                                                                                                                   
         AND wh.dayOfWeek = :day                                                                                                                                                                                                                                    
         AND :currentTime BETWEEN wh.openingTime AND wh.closingTime                                                                                                                                                                                                 
     """)
    Page<Restaurant> findOpenRestaurantsNow(
            @Param("day") DayOfWeek day,
            @Param("currentTime") LocalTime currentTime,
            Pageable pageable
    );
}
