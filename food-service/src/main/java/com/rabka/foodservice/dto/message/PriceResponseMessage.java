package com.rabka.foodservice.dto.message;

import java.math.BigDecimal;
import java.util.List;

public record PriceResponseMessage(
        String correlationId,
        BigDecimal totalPrice,
        List<PriceResponseItem> items
) {
}
