package com.Rabka.rabka.service;

import com.Rabka.rabka.dto.WorkingHours.WorkingHoursCreateDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursResponseDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursUpdateDto;

public interface WorkingHoursService {
    WorkingHoursResponseDto createWorkingHours(WorkingHoursCreateDto workingHoursCreateDto);
    void deleteWorkingHours(Long id);
    WorkingHoursResponseDto getWorkingHours(Long id);
    WorkingHoursResponseDto updateWorkingHours(WorkingHoursUpdateDto workingHoursUpdateDto);

}
