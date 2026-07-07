package com.Rabka.rabka.mapstruct;


import com.Rabka.rabka.dto.WorkingHours.WorkingHoursCreateDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursResponseDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursUpdateDto;
import com.Rabka.rabka.entity.WorkingHours;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkingHoursMapper {
    WorkingHours workingHoursResponseDtoToWorkingHours(WorkingHoursResponseDto workingHoursResponseDto);
    WorkingHours workingHoursCreateDtoToWorkingHours(WorkingHoursCreateDto workingHoursCreateDto);
    WorkingHours workingHoursUpdateDtoToWorkingHours(WorkingHoursUpdateDto workingHoursUpdateDto);
    WorkingHoursResponseDto workingHoursToWorkingHoursResponseDto(WorkingHours workingHours);
}
