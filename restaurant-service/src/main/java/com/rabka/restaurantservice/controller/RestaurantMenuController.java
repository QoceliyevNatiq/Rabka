package com.rabka.restaurantservice.controller;

import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import com.rabka.restaurantservice.service.RestaurantMenuService;
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
@RequestMapping("/api/restaurant-menu")
@RequiredArgsConstructor
@Slf4j
public class RestaurantMenuController {

    private final RestaurantMenuService restaurantMenuService;

    @PostMapping
    public ResponseEntity<RestaurantMenuResponseDto> create(@RequestBody @Valid RestaurantMenuCreateDto dto) {
        log.debug("Rest request to create restaurant menu | name: {}", dto.name());
        RestaurantMenuResponseDto response = restaurantMenuService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        log.info("Rest request to create restaurant menu | id: {}", response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<RestaurantMenuResponseDto> update(@RequestBody @Valid RestaurantMenuUpdateDto dto) {
        log.debug("Rest request to update restaurant menu | id: {}", dto.id());
        RestaurantMenuResponseDto response = restaurantMenuService.update(dto);
        log.info("Rest request to update restaurant menu | id: {}", response.id());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("Rest request to delete restaurant menu | id: {}", id);
        restaurantMenuService.deleteById(id);
        log.info("Rest request to delete restaurant menu | id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantMenuResponseDto> getById(@PathVariable Long id) {
        log.debug("Rest request to get restaurant menu | id: {}", id);
        RestaurantMenuResponseDto response = restaurantMenuService.findById(id);
        log.info("Rest request to get restaurant menu | id: {}", id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantMenuResponseDto>> getAll(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("Rest request to get all restaurant menus");
        Page<RestaurantMenuResponseDto> response = restaurantMenuService.findAll(pageable);
        log.info("Rest request to get all restaurant menus | totalElements: {}", response.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
