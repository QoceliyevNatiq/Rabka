package com.rabka.orderservice.service.impl;

import com.rabka.orderservice.client.RestaurantClient;
import com.rabka.orderservice.client.RestaurantInfo;
import com.rabka.orderservice.dto.Order.OrderCreateDto;
import com.rabka.orderservice.dto.Order.OrderResponseDto;
import com.rabka.orderservice.dto.Order.OrderSummaryResponseDto;
import com.rabka.orderservice.dto.message.PriceRequestItem;
import com.rabka.orderservice.dto.message.PriceResponseMessage;
import com.rabka.orderservice.dto.orderitem.OrderItemCreateDto;
import com.rabka.orderservice.entity.Location;
import com.rabka.orderservice.entity.Order;
import com.rabka.orderservice.entity.OrderItem;
import com.rabka.orderservice.entity.OrderStatus;
import com.rabka.orderservice.mapper.OrderItemMapper;
import com.rabka.orderservice.mapper.OrderMapper;
import com.rabka.orderservice.messaging.PriceRequestPublisher;
import com.rabka.orderservice.repository.OrderRepository;
import com.rabka.orderservice.service.OrderService;
import com.rabka.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final RestaurantClient restaurantClient;
    private final PriceRequestPublisher priceRequestPublisher;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderCreateDto dto, Long userId) {
        log.debug("createOrder started | userId: {} restaurantId: {}", userId, dto.restaurantId());

        RestaurantInfo restaurant = restaurantClient.getRestaurantById(dto.restaurantId());
        if (restaurant == null) {
            throw new ResourceNotFoundException("Restaurant", "id", dto.restaurantId());
        }

        Location location = new Location(dto.latitude(), dto.longitude());

        Order order = Order.builder()
                .restaurantId(dto.restaurantId())
                .userId(userId)
                .location(location)
                .orderStatus(OrderStatus.PREPARED)
                .orderItem(new ArrayList<>())
                .price(BigDecimal.ZERO)
                .build();

        for (OrderItemCreateDto itemDto : dto.orderItemCreateDtos()) {
            OrderItem item = orderItemMapper.createDtoToOrderItem(itemDto);
            item.setOrder(order);
            order.getOrderItem().add(item);
        }

        List<PriceRequestItem> priceItems = order.getOrderItem().stream()
                .map(item -> new PriceRequestItem(item.getFood(), item.getQuantity()))
                .toList();

        PriceResponseMessage priceResponse = priceRequestPublisher.requestPrice(0L, priceItems);

        for (int i = 0; i < order.getOrderItem().size(); i++) {
            OrderItem item = order.getOrderItem().get(i);
            item.setPriceAtPurchase(priceResponse.items().get(i).subtotal().doubleValue());
        }

        order.setPrice(priceResponse.totalPrice());

        Order savedOrder = orderRepository.save(order);
        log.info("Order created | id: {} totalPrice: {}", savedOrder.getId(), savedOrder.getPrice());

        return orderMapper.orderToResponseDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        log.debug("getOrderById | id: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        return orderMapper.orderToResponseDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        log.debug("updateOrderStatus | id: {} status: {}", orderId, orderStatus);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        order.setOrderStatus(orderStatus);
        Order updatedOrder = orderRepository.save(order);

        log.info("Order status updated | id: {} newStatus: {}", orderId, orderStatus);
        return orderMapper.orderToResponseDto(updatedOrder);
    }

    @Override
    public Page<OrderSummaryResponseDto> getOrdersByUserId(Long userId, Pageable pageable) {
        log.debug("getOrdersByUserId | userId: {}", userId);

        Page<Order> orders = orderRepository.findByUserId(userId, pageable);

        return orders.map(order -> new OrderSummaryResponseDto(
                order.getId(),
                order.getRestaurantId(),
                order.getPrice(),
                order.getOrderStatus(),
                order.getOrderTime(),
                order.getOrderItem() != null ? order.getOrderItem().size() : 0
        ));
    }
}
