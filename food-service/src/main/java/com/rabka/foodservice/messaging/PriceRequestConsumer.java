package com.rabka.foodservice.messaging;

import com.rabka.foodservice.config.RabbitMQConfig;
import com.rabka.foodservice.dto.message.PriceRequestItem;
import com.rabka.foodservice.dto.message.PriceRequestMessage;
import com.rabka.foodservice.dto.message.PriceResponseItem;
import com.rabka.foodservice.dto.message.PriceResponseMessage;
import com.rabka.foodservice.entity.Food;
import com.rabka.foodservice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceRequestConsumer {

    private final FoodRepository foodRepository;

    @RabbitListener(queues = RabbitMQConfig.PRICE_REQUEST_QUEUE)
    public PriceResponseMessage handlePriceRequest(PriceRequestMessage request) {
        log.info("Aldi: orderId={}", request.orderId());

        List<PriceResponseItem> responseItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (PriceRequestItem item : request.item()) {
            Food food = foodRepository.findById(item.foodId())
                    .orElseThrow(() -> new RuntimeException("Food tapilmadi: " + item.foodId()));

            BigDecimal unitPrice = food.getPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(item.quantity()));

            responseItems.add(new PriceResponseItem(
                    item.foodId(),
                    unitPrice,
                    item.quantity(),
                    subtotal
            ));

            totalPrice = totalPrice.add(subtotal);
        }

        log.info("Gonderilir cavab: totalPrice={}", totalPrice);

        return new PriceResponseMessage(
                request.correlationId(),
                totalPrice,
                responseItems
        );
    }
}
