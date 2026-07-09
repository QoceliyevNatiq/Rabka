package com.rabka.orderservice.dto.message;

import com.rabka.orderservice.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEvent(
        String eventId,
        Long orderId,
        Long userId,
        Long restaurantId,
        BigDecimal totalPrice,
        LocalDateTime orderTime,
        OrderStatus orderStatus
) {
}
