package com.rabka.restaurantservice.service.impl;

import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuCreateDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuResponseDto;
import com.rabka.restaurantservice.dto.RestaurantMenu.RestaurantMenuUpdateDto;

import com.rabka.restaurantservice.entity.RestaurantMenu;
import com.rabka.restaurantservice.mapstruct.RestaurantMenuMapper;
import com.rabka.restaurantservice.repository.RestaurantMenuRepository;
import com.rabka.restaurantservice.repository.RestaurantRepository;
import com.rabka.restaurantservice.service.RestaurantMenuService;
import com.rabka.common.exception.ResourceNotFoundException;
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
    private final RestaurantMenuMapper mapper;
    private final RestaurantRepository restaurantRepository;

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
        log.debug("RestaurantMenu create start");
        if(!restaurantRepository.existsById(dto.restaurantId())) throw new ResourceNotFoundException("create","restaurantId",dto.restaurantId());
        restaurantMenuRepository.save(mapper.restaurantMenuCreateDtoToRestaurantMenu(dto));
        log.info("RestaurantMenu create ended | id: {}", dto.restaurantId());
        return  mapper.restaurantCreateToRestaurantMenuResponseDto(dto);
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
        return mapper.restaurantMenuToRestaurantMenuResponseDto(restaurantMenu);
    }

    @Override
    public RestaurantMenuResponseDto findById(Long id) {
        log.debug("RestaurantMenu findById start | id: {}", id);
        RestaurantMenu restaurantMenu = restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("findById", "id", id));
        return mapper.restaurantMenuToRestaurantMenuResponseDto(restaurantMenu);
    }

    @Override
    public Page<RestaurantMenuResponseDto> findAll(Pageable pageable) {
        log.debug("RestaurantMenu findAll start");
        return restaurantMenuRepository.findAll(pageable)
                .map(mapper::restaurantMenuToRestaurantMenuResponseDto);
    }
}
