package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.dto.WorkingHours.WorkingHoursCreateDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursResponseDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursUpdateDto;
import com.Rabka.rabka.entity.RestaurantBranch;
import com.Rabka.rabka.entity.WorkingHours;
import com.Rabka.rabka.exception.ResourceNotFoundException;
import com.Rabka.rabka.mapstruct.WorkingHoursMapper;
import com.Rabka.rabka.repo.RestaurantBranchRepository;
import com.Rabka.rabka.repo.WorkingHoursRepository;
import com.Rabka.rabka.service.WorkingHoursService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class WorkingHoursServiceImpl implements WorkingHoursService {
    private final WorkingHoursRepository workingHoursRepository;
    private final WorkingHoursMapper mapper;
    private final RestaurantBranchRepository restaurantBranchRepository;


    @Override
    @Transactional
    public WorkingHoursResponseDto createWorkingHours(WorkingHoursCreateDto workingHoursCreateDto) {
        log.debug("createWorkingHours start | restaurantBranchId: {}",workingHoursCreateDto.restaurantBranchId());
        RestaurantBranch branch = restaurantBranchRepository.findById(workingHoursCreateDto.restaurantBranchId())
                .orElseThrow(() ->
         {
            log.warn("createWorkingHours: restaurantBranchId is null");
            return new ResourceNotFoundException("createWorkingHours", "restaurantBranchId", workingHoursCreateDto.restaurantBranchId());
        });
        WorkingHours workingHours = mapper.workingHoursCreateDtoToWorkingHours(workingHoursCreateDto);
        workingHours.setRestaurantBranch(branch);
        workingHoursRepository.save(workingHours);
        log.info("createWorkingHours ended | restaurantBranchId: {}",workingHoursCreateDto.restaurantBranchId());
        return mapper.workingHoursToWorkingHoursResponseDto(workingHours);
    }

    @Override
    @Transactional
    public void deleteWorkingHours(Long id) {
        log.debug("deleteWorkingHours start | WorkingHoursId: {}",id);
        WorkingHours workingHours = workingHoursRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("deleteWorkingHours: workingHoursId: {}",id);
                    return new ResourceNotFoundException("deleteWorkingHours", "WorkingHoursId", id);
                });
        workingHoursRepository.delete(workingHours);
        log.info("deleteWorkingHours ended | WorkingHoursId: {}",id);

    }

    @Override
    public WorkingHoursResponseDto getWorkingHours(Long id) {
        log.debug("getWorkingHours start | WorkingHoursId: {}",id);
      WorkingHours workingHours = workingHoursRepository.findById(id)
              .orElseThrow(() -> {
                  log.warn("getWorkingHours: workingHoursId: {}",id);
                  return new ResourceNotFoundException("getWorkingHours", "WorkingHoursId", id);
              });
      log.info("getWorkingHours ended | WorkingHoursId: {}",id);
      return mapper.workingHoursToWorkingHoursResponseDto(workingHours);
    }

    @Override
    @Transactional
    public WorkingHoursResponseDto updateWorkingHours(WorkingHoursUpdateDto workingHoursUpdateDto) {
        log.debug("updateWorkingHours start | WorkingHOursId: {}", workingHoursUpdateDto.id());
        WorkingHours workingHours = workingHoursRepository.findById(workingHoursUpdateDto.id())
                .orElseThrow(() -> {
                    log.warn("updateWorkingHours: workingHoursId: {}",workingHoursUpdateDto.id());
                    return new ResourceNotFoundException("updateWorkingHours", "id", workingHoursUpdateDto.id());
                });
        if(workingHoursUpdateDto.closingTime()!=null){
            workingHours.setClosingTime(workingHoursUpdateDto.closingTime());
        }
        if(workingHoursUpdateDto.openingTime()!=null){
            workingHours.setOpeningTime(workingHoursUpdateDto.openingTime());
        }
        if(workingHoursUpdateDto.dayOfWeek()!=null){
            workingHours.setDayOfWeek(workingHoursUpdateDto.dayOfWeek());
        }
        workingHoursRepository.save(workingHours);
        log.info("updateWorkingHours ended | workingHoursId: {}",workingHoursUpdateDto.id());
        return mapper.workingHoursToWorkingHoursResponseDto(workingHours);

    }

    @Override
    public Page<WorkingHoursResponseDto> getWorkingHoursForRestaurantBranch(Pageable pageable, Long restaurantBranchId) {
        log.debug("getWorkingHours start | restaurantBranchId: {}",restaurantBranchId);
        RestaurantBranch branch = restaurantBranchRepository.findById(restaurantBranchId)
                .orElseThrow(() -> {
                    log.warn("getWorkingHours: restaurantBranchId: {}",restaurantBranchId);
                    return new ResourceNotFoundException("getWorkingHours", "restaurantBranchId", restaurantBranchId);
                        });
        Page<WorkingHoursResponseDto> responses = workingHoursRepository.findWorkingHoursByRestaurantBranchId(restaurantBranchId,pageable)
                .map(mapper::workingHoursToWorkingHoursResponseDto);
        log.info("getWorkingHours ended | restaurantBranchId: {}",restaurantBranchId);
        return responses;
    }
}
