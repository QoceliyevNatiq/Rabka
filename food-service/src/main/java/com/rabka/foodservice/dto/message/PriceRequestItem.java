package com.rabka.foodservice.dto.message;

public record PriceRequestItem(
        Long foodId,
        Integer quantity
) {
}
