package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.Rabka.rabka.dto.RestaurantMenu.RestaurantMenuUpdateDto;
import com.Rabka.rabka.entity.RestaurantMenu;
import com.Rabka.rabka.exception.ResourceNotFoundException;
import com.Rabka.rabka.repo.RestaurantMenuRepository;
import com.Rabka.rabka.service.RestaurantMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantMenuServiceImpl implements RestaurantMenuService {

    private final RestaurantMenuRepository restaurantMenuRepository;
    private final

    @Override
    public void deleteById(Long menuId) {
        log.debug("Delete RestaurantMenu start | id: {}", menuId);
        if(!restaurantMenuRepository.existsById(menuId)){
            throw new ResourceNotFoundException("deleteById","menuId",menuId);
        }
        restaurantMenuRepository.deleteById(menuId);
        log.info("Delete RestaurantMenu ended | id: {}", menuId);
    }

    @Override
    public RestaurantMenuResponseDto create(RestaurantMenuCreateDto dto) {
//        log.debug("RestaurantMenu create start");

    }

    @Override
    @Transactional
    public RestaurantMenuResponseDto update(RestaurantMenuUpdateDto dto) {
        log.debug("RestaurantMenu update start | id: {}", dto.id());
        RestaurantMenu restaurantMenu = restaurantMenuRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("RestaurantMenu", "id", dto.id()));
        if(dto.name() != null){
            restaurantMenu.setName(dto.name());
        }
        restaurantMenu.setName(dto.name());
        log.info("RestaurantMenu update ended | id: {}", dto.id());
        return mapper
    }

    @Override
    public RestaurantMenuResponseDto findById(Long id) {
        return null;
    }

    @Override
    public Page<RestaurantMenuResponseDto> findAll(Pageable pageable) {
        return null;
    }
}
