package com.rabka.orderservice.dto.orderitem;

public record OrderItemResponseDto(
        Long id,
        Double priceAtPurchase,
        Long foodId,
        Integer quantity
) {
}
