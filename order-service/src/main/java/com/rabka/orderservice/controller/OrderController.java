package com.rabka.orderservice.controller;

import com.rabka.orderservice.dto.Order.OrderCreateDto;
import com.rabka.orderservice.dto.Order.OrderResponseDto;
import com.rabka.orderservice.dto.Order.OrderSummaryResponseDto;
import com.rabka.orderservice.entity.OrderStatus;
import com.rabka.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody @Valid OrderCreateDto dto) {
        log.debug("POST /api/order | userId: {}", userId);
        return orderService.createOrder(dto, userId);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrderById(@PathVariable Long id) {
        log.debug("GET /api/order/{}", id);
        return orderService.getOrderById(id);
    }

    @PatchMapping("/{id}/status")
    public OrderResponseDto updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        log.debug("PATCH /api/order/{}/status | status: {}", id, status);
        return orderService.updateOrderStatus(id, status);
    }

    @GetMapping("/my")
    public Page<OrderSummaryResponseDto> getMyOrders(
            @RequestHeader("X-User-Id") Long userId,
            @PageableDefault(size = 20, sort = "orderTime") Pageable pageable) {
        log.debug("GET /api/order/my | userId: {}", userId);
        return orderService.getOrdersByUserId(userId, pageable);
    }
}
