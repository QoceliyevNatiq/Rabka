package com.Rabka.rabka.service;

import com.Rabka.rabka.dto.WorkingHours.WorkingHoursCreateDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursResponseDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursUpdateDto;
import com.Rabka.rabka.entity.RestaurantBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface WorkingHoursService {
    WorkingHoursResponseDto createWorkingHours(WorkingHoursCreateDto workingHoursCreateDto);
    void deleteWorkingHours(Long id);
    WorkingHoursResponseDto getWorkingHours(Long id);
    WorkingHoursResponseDto updateWorkingHours(WorkingHoursUpdateDto workingHoursUpdateDto);
    Page<WorkingHoursResponseDto> getWorkingHoursForRestaurantBranch(Pageable pageable, Long restaurantBranchId);


}
