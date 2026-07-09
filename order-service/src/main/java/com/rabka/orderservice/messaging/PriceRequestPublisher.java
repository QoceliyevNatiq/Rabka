package com.rabka.orderservice.messaging;

import com.rabka.orderservice.config.RabbitMQConfig;
import com.rabka.orderservice.dto.message.PriceRequestItem;
import com.rabka.orderservice.dto.message.PriceRequestMessage;
import com.rabka.orderservice.dto.message.PriceResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceRequestPublisher {

    private final RabbitTemplate template;

    public PriceResponseMessage requestPrice(Long orderId, List<PriceRequestItem> items) {
        String correlationId = UUID.randomUUID().toString();

        PriceRequestMessage request = new PriceRequestMessage(
                correlationId,
                orderId,
                items
        );

        log.info("Gonderilir: orderId={}, correlationId={}", orderId, correlationId);

        Object response = template.convertSendAndReceive(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.PRICE_REQUEST_KEY,
                request
        );

        if (response == null) {
            throw new RuntimeException("Food service cavab vermədi: " + orderId);
        }

        log.info("Aldi: totalPrice={}", ((PriceResponseMessage) response).totalPrice());
        return (PriceResponseMessage) response;
    }
}
