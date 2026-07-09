package com.rabka.orderservice.dto.message;

public record PriceRequestItem(
        Long foodId,
        Integer quantity
) {
}
