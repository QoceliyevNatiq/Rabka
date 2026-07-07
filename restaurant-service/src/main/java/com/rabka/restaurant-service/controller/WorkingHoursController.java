package com.Rabka.rabka.controller;

import com.Rabka.rabka.dto.WorkingHours.WorkingHoursCreateDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursResponseDto;
import com.Rabka.rabka.dto.WorkingHours.WorkingHoursUpdateDto;
import com.Rabka.rabka.service.WorkingHoursService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/working-hours")
@RequiredArgsConstructor
@Slf4j
public class WorkingHoursController {

    private final WorkingHoursService workingHoursService;

    @PostMapping
    public ResponseEntity<WorkingHoursResponseDto> create(@RequestBody @Valid WorkingHoursCreateDto dto) {
        log.debug("Rest request to create working hours | branchId: {}", dto.restaurantBranchId());
        WorkingHoursResponseDto response = workingHoursService.createWorkingHours(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        log.info("Rest request to create working hours | id: {}", response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<WorkingHoursResponseDto> update(@RequestBody @Valid WorkingHoursUpdateDto dto) {
        log.debug("Rest request to update working hours | id: {}", dto.id());
        WorkingHoursResponseDto response = workingHoursService.updateWorkingHours(dto);
        log.info("Rest request to update working hours | id: {}", response.id());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("Rest request to delete working hours | id: {}", id);
        workingHoursService.deleteWorkingHours(id);
        log.info("Rest request to delete working hours | id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingHoursResponseDto> getById(@PathVariable Long id) {
        log.debug("Rest request to get working hours | id: {}", id);
        WorkingHoursResponseDto response = workingHoursService.getWorkingHours(id);
        log.info("Rest request to get working hours | id: {}", id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<Page<WorkingHoursResponseDto>> getByBranch(@PathVariable Long branchId, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to get working hours by branch | branchId: {}", branchId);
        Page<WorkingHoursResponseDto> response = workingHoursService.getWorkingHoursForRestaurantBranch(pageable, branchId);
        log.info("Rest request to get working hours by branch | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
