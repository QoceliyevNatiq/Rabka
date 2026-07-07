package com.rabka.restaurantservice.repository;


import com.rabka.restaurantservice.entity.WorkingHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours,Long> {

    Page<WorkingHours> findWorkingHoursByRestaurantBranchId(Long restaurantBranchId, Pageable pageable);
}
