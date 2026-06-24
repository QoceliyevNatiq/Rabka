package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.exception.ResourceNotFoundException;
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
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;
    private final RepositoryMethodInvocationListener repositoryMethodInvocationListener;


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
                    log.warn("deleteRestaurant - Restaurant not found with id: {}", updateDto.id());
                    return new ResourceNotFoundException("updateRestaurant","restaurantId", updateDto.id());
                });
        if(updateDto.logoUrl() != null) {
            restaurant.setLogoUrl(updateDto.logoUrl());
        }
        if(updateDto.name() != null) {
            restaurant.setName(updateDto.name());
        }
        if(updateDto.description() != null) {
            restaurant.setDescription(updateDto.description());
        }
        if(updateDto.type() != null) {
            restaurant.setType(updateDto.type());
        }
        if(updateDto.isActive() != null) {
            restaurant.setIsActive(updateDto.isActive());
        }
        log.info("updateRestaurant - end | Restaurant id: {}",restaurant.getId());
        return  mapper.restaurantToRestaurantDto(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        log.debug("getRestaurant - start | Restaurant id: {}", id);
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("getRestaurant - Restaurant not found with id: {}", id);
                    return new ResourceNotFoundException("getRestaurant","restaurantId", id);
                });
        RestaurantResponseDto responseDto = mapper.restaurantToRestaurantDto(restaurant);
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
