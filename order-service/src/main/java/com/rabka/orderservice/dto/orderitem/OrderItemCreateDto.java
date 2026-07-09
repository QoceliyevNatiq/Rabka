package com.rabka.orderservice.dto.orderitem;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrderItemCreateDto(
        @NotNull(message = "Food id teleb olunur")
        @Positive(message = "Food id musbet olmalidir")
        Long foodId,
        @NotNull(message = "Miqdar teleb olunur")
        @Min(value = 1, message = "En azi 1 eded olmalidir")
        @Max(value = 99,message = "en coxu 99 eded ola biler")
        Long quantity,

        @Size(max = 200, message = "Qeyd 200 simvoldan cox ola bilmez")
        String note
) {
}
