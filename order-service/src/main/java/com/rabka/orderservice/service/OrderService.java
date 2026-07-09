package com.rabka.orderservice.service;

import com.rabka.orderservice.dto.Order.OrderCreateDto;
import com.rabka.orderservice.dto.Order.OrderResponseDto;
import com.rabka.orderservice.dto.Order.OrderSummaryResponseDto;
import com.rabka.orderservice.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(OrderCreateDto orderCreateDto, Long userId);
    OrderResponseDto getOrderById(Long orderId);
    OrderResponseDto updateOrderStatus(Long orderId, OrderStatus orderStatus);
    Page<OrderSummaryResponseDto> getOrdersByUserId(Long userId, Pageable pageable);
}
