package com.rabka.restaurantservice.controller;

import com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto;
import com.rabka.restaurantservice.entity.RestaurantStatus;
import com.rabka.restaurantservice.entity.RestaurantType;
import com.rabka.restaurantservice.service.RestaurantService;
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
import java.sql.Time;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto> create(@RequestBody @Valid com.rabka.restaurantservice.dto.Restauran.RestaurantCreateDto dto) {
        log.debug("Rest request to create restaurant | name: {}", dto.name());
        com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto response = restaurantService.createRestaurant(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        log.info("Rest request to create restaurant | id: {}", response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto> update(@RequestBody @Valid com.rabka.restaurantservice.dto.Restauran.RestaurantUpdateDto dto) {
        log.debug("Rest request to update restaurant | id: {}", dto.id());
        com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto response = restaurantService.updateRestaurant(dto);
        log.info("Rest request to update restaurant | id: {}", response.id());
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto> updateStatus(@PathVariable Long id, @RequestParam RestaurantStatus status) {
        log.debug("Rest request to update restaurant status | id: {}, status: {}", id, status);
        com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto response = restaurantService.updateRestaurantStatus(id, status);
        log.info("Rest request to update restaurant status | id: {}", response.id());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/active")
    public ResponseEntity<Page<com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto>> getAllActive(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to get all active restaurants");
        Page<com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto> response = restaurantService.getAllRestaurantsIsActive(pageable);
        log.info("Rest request to get all active restaurants | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/open")
    public ResponseEntity<Page<com.rabka.restaurantservice.dto.Restauran.RestaurantResponseDto>> getOpenRestaurants(@RequestParam Time now, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to get open restaurants | now: {}", now);
        Page<RestaurantResponseDto> response = restaurantService.findRestaurantsIsNotClosed(pageable, now);
        log.info("Rest request to get open restaurants | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RestaurantResponseDto>> searchByName(@RequestParam String name, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to search restaurants by name | name: {}", name);
        Page<RestaurantResponseDto> response = restaurantService.findRestaurantsByNameContainingIgnoreCase(name, pageable);
        log.info("Rest request to search restaurants by name | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Page<RestaurantResponseDto>> getByType(@PathVariable RestaurantType type, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to get restaurants by type | type: {}", type);
        Page<RestaurantResponseDto> response = restaurantService.findRestaurantsByType(type, pageable);
        log.info("Rest request to get restaurants by type | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("Rest request to delete restaurant | id: {}", id);
        restaurantService.deleteRestaurantById(id);
        log.info("Rest request to delete restaurant | id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getById(@PathVariable Long id) {
        log.debug("Rest request to get restaurant | id: {}", id);
        RestaurantResponseDto response = restaurantService.getRestaurantById(id);
        log.info("Rest request to get restaurant | id: {}", id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDto>> getAll(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to get all restaurants");
        Page<RestaurantResponseDto> response = restaurantService.getAllRestaurants(pageable);
        log.info("Rest request to get all restaurants | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
