package com.rabka.orderservice.mapper;

import com.rabka.orderservice.dto.orderitem.OrderItemCreateDto;
import com.rabka.orderservice.dto.orderitem.OrderItemResponseDto;
import com.rabka.orderservice.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "food", target = "foodId")
    OrderItemResponseDto orderItemToResponseDto(OrderItem orderItem);

    @Mapping(source = "foodId", target = "food")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "priceAtPurchase", ignore = true)
    OrderItem createDtoToOrderItem(OrderItemCreateDto dto);
}
