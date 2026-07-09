package com.rabka.orderservice.service.impl;

import com.rabka.orderservice.dto.orderitem.OrderItemCreateDto;
import com.rabka.orderservice.dto.orderitem.OrderItemResponseDto;
import com.rabka.orderservice.entity.Order;
import com.rabka.orderservice.entity.OrderItem;
import com.rabka.orderservice.mapsturct.OrderItemMapper;
import com.rabka.orderservice.repository.OrderItemRepository;
import com.rabka.orderservice.repository.OrderRepository;
import com.rabka.orderservice.service.OrderItemService;
import com.rabka.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemResponseDto getOrderItemById(Long orderItemId) {
        log.debug("getOrderItemById | id: {}", orderItemId);

        OrderItem item = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", orderItemId));

        return orderItemMapper.orderItemToResponseDto(item);
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId) {
        log.debug("getOrderItemsByOrderId | orderId: {}", orderId);

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        return items.stream()
                .map(orderItemMapper::orderItemToResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderItemResponseDto createOrderItem(OrderItemCreateDto dto) {
        log.debug("createOrderItem | foodId: {}", dto.foodId());

        OrderItem item = orderItemMapper.createDtoToOrderItem(dto);

        BigDecimal itemPrice = BigDecimal.valueOf(dto.quantity() * 10.0);
        item.setPriceAtPurchase(itemPrice.doubleValue());

        OrderItem savedItem = orderItemRepository.save(item);
        log.info("OrderItem created | id: {} foodId: {}", savedItem.getId(), savedItem.getFood());

        return orderItemMapper.orderItemToResponseDto(savedItem);
    }
}
