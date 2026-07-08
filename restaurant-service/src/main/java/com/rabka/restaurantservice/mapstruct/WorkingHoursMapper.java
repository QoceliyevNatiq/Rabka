package com.rabka.restaurantservice.mapstruct;

import org.mapstruct.Mapper;

import com.rabka.restaurantservice.dto.WorkingHours.WorkingHoursCreateDto;
import com.rabka.restaurantservice.dto.WorkingHours.WorkingHoursResponseDto;
import com.rabka.restaurantservice.dto.WorkingHours.WorkingHoursUpdateDto;
import com.rabka.restaurantservice.entity.WorkingHours;


@Mapper(componentModel = "spring")
public interface WorkingHoursMapper {
    WorkingHours workingHoursResponseDtoToWorkingHours(WorkingHoursResponseDto workingHoursResponseDto);
    WorkingHours workingHoursCreateDtoToWorkingHours(WorkingHoursCreateDto workingHoursCreateDto);
    WorkingHours workingHoursUpdateDtoToWorkingHours(WorkingHoursUpdateDto workingHoursUpdateDto);
    WorkingHoursResponseDto workingHoursToWorkingHoursResponseDto(WorkingHours workingHours);
}
