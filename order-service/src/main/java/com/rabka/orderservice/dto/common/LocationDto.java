package com.rabka.orderservice.dto.common;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record LocationDto(
        @NotNull(message = "Latitude tələb olunur")
        @DecimalMin(value = "-90.0", message = "Latitude -90 ilə 90 arasında olmalıdır")
        @DecimalMax(value = "90.0", message = "Latitude -90 ilə 90 arasında olmalıdır")
        double latitude,

        @NotNull(message = "Longitude tələb olunur")
        @DecimalMin(value = "-180.0", message = "Longitude -180 ilə 180 arasında olmalıdır")
        @DecimalMax(value = "180.0", message = "Longitude -180 ilə 180 arasında olmalıdır")
        double longitude
) {
}
