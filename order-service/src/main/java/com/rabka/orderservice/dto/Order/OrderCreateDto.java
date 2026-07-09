package com.rabka.orderservice.dto.Order;

import com.rabka.orderservice.dto.orderitem.OrderItemCreateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record OrderCreateDto(


        @NotNull(message = "Mutleq bir restorant secilmelidir")
        @Positive(message = "User id müsbət olmalıdır")
        Long restaurantId,

        @NotEmpty(message = "en azi bir product secilmelidir")
        List<@Valid OrderItemCreateDto> orderItemCreateDtos,

        @NotNull(message = "Latitude mütləqdir")
        @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
        @DecimalMax(value = "90.0", message = "Yanlış latitude dəyəri")
        double latitude,

        @NotNull(message = "Latitude mütləqdir")
        @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
        @DecimalMax(value = "180.0", message = "Yanlış longitude dəyəri")
        double longitude

) {
}
