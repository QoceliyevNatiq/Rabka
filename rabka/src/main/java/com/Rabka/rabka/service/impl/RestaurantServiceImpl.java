package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.mapstruct.RestaurantMapper;
import com.Rabka.rabka.mapstruct.UserMapper;
import com.Rabka.rabka.dto.Restauran.RestaurantCreateDto;
import com.Rabka.rabka.dto.Restauran.RestaurantResponseDto;
import com.Rabka.rabka.dto.Restauran.RestaurantUpdateDto;
import com.Rabka.rabka.entity.Restaurant;
import com.Rabka.rabka.entity.RestaurantStatus;
import com.Rabka.rabka.entity.RestaurantType;
import com.Rabka.rabka.repo.RestaurantRepository;
import com.Rabka.rabka.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;


    @Override
    @Transactional
    public RestaurantResponseDto createRestaurant(RestaurantCreateDto createDto) {
        log.debug("createRestaurant - start");
        Restaurant restaurant = mapper.restaurantCreateDtoToRestaurant(createDto);
        restaurantRepository.save(restaurant);
        log.info("createRestaurant - end | Restaurant id: {}",restaurant.getId());
        return mapper.restaurantToRestaurantDto(restaurant);
    }

    @Override
    public RestaurantResponseDto updateRestaurant(RestaurantUpdateDto updateDto) {
        log.debug("updateRestaurant - start | RestaurantId: {}", updateDto.id());
        Restaurant restaurant = restaurantRepository.findById(updateDto.id())
                .orElseThrow(() -> {
                    log.warn("")
                }
    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        return null;
    }

    @Override
    public void deleteRestaurantById(Long id) {

    }

    @Override
    public Page<RestaurantResponseDto> getAllRestaurantsIsActive(Pageable pageable) {
        return null;
    }

    @Override
    public Page<RestaurantResponseDto> getAllRestaurants(Pageable pageable) {
        return null;
    }

    @Override
    public Page<RestaurantResponseDto> findRestaurantsByType(RestaurantType type, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RestaurantResponseDto> findRestaurantsByNameContainingIgnoreCase(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<RestaurantResponseDto> findRestaurantsIsClosed(Pageable pageable) {
        return null;
    }

    @Override
    public RestaurantResponseDto updateRestaurantStatus(Long id, RestaurantStatus status) {
        return null;
    }
}
