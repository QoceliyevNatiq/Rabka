package com.rabka.foodservice.dto.message;

import java.math.BigDecimal;

public record PriceResponseItem(
        Long foodId,
        BigDecimal unitPrice,
        Integer quantity,
        BigDecimal subtotal
) {
}
