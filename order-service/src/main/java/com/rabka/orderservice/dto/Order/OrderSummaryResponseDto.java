package com.rabka.orderservice.dto.Order;

import com.rabka.orderservice.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderSummaryResponseDto(
        Long id,
        Long restaurantId,
        BigDecimal totalPrice,
        OrderStatus orderStatus,
        LocalDateTime orderTime,
        Integer itemCount
) {
}
