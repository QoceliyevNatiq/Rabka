package com.rabka.orderservice.dto.Order;

import com.rabka.orderservice.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;import jakarta.validation.constraints.NotNull;

public record OrderUpdateDto(
        @NotNull(message = "Id bos ola bilmez")
        Long orderId,

        @NotBlank(message = "Status teleb olunur")
        OrderStatus orderStatus
) {
}
