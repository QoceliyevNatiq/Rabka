package com.rabka.orderservice.service;

import com.rabka.orderservice.dto.orderitem.OrderItemCreateDto;
import com.rabka.orderservice.dto.orderitem.OrderItemResponseDto;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDto getOrderItemById(Long orderItemId);
    List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId);
    OrderItemResponseDto createOrderItem(OrderItemCreateDto orderItemCreateDto);

}
