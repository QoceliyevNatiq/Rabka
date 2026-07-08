package com.rabka.restaurantservice.service;

import com.rabka.restaurantservice.dto.WorkingHours.WorkingHoursCreateDto;
import com.rabka.restaurantservice.dto.WorkingHours.WorkingHoursResponseDto;
import com.rabka.restaurantservice.dto.WorkingHours.WorkingHoursUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkingHoursService {
    WorkingHoursResponseDto createWorkingHours(WorkingHoursCreateDto workingHoursCreateDto);
    void deleteWorkingHours(Long id);
    WorkingHoursResponseDto getWorkingHours(Long id);
    WorkingHoursResponseDto updateWorkingHours(WorkingHoursUpdateDto workingHoursUpdateDto);
    Page<WorkingHoursResponseDto> getWorkingHoursForRestaurantBranch(Pageable pageable, Long restaurantBranchId);


}
