package com.rabka.orderservice.dto.Order;

import com.rabka.orderservice.dto.orderitem.OrderItemResponseDto;
import com.rabka.orderservice.entity.OrderItem;
import com.rabka.orderservice.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,
        BigDecimal price,
        Long restaurantId,
        List<OrderItemResponseDto> orderItems,
        LocalDateTime orderTime,
        OrderStatus orderStatus,
        Long userId,
        double latitude,
        double longitude
) {
}
