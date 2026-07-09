package com.rabka.orderservice.mapsturct;

import com.rabka.orderservice.dto.Order.OrderCreateDto;
import com.rabka.orderservice.dto.Order.OrderResponseDto;
import com.rabka.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(source = "location.latitude", target = "latitude")
    @Mapping(source = "location.longitude", target = "longitude")
    @Mapping(source = "orderItem", target = "orderItems")
    OrderResponseDto orderToResponseDto(Order order);

    @Mapping(source = "latitude", target = "location.latitude")
    @Mapping(source = "longitude", target = "location.longitude")
    @Mapping(source = "orderItemCreateDtos", target = "orderItem")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTime", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Order createDtoToOrder(OrderCreateDto dto);

    OrderResponseDto orderToOrderResponseDto(Order order);
    Order orderCreateDtoToOrder(OrderCreateDto dto);

}
