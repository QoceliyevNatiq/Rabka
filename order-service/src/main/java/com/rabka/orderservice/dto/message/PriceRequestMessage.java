package com.rabka.orderservice.dto.message;

import java.util.List;

public record PriceRequestMessage(
        String correlationId,
        Long orderId,
        List<PriceRequestItem> item
) {
}
