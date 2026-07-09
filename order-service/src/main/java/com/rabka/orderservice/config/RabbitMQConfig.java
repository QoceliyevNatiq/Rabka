package com.rabka.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "order.exchange";
    public static final String PRICE_REQUEST_QUEUE = "price.request.queue";
    public static final String PRICE_REQUEST_KEY = "price.request";
    public static final String ORDER_EVENTS_QUEUE = "order.events.queue";
    public static final String ORDER_EVENTS_KEY = "order.events";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue priceRequestQueue() {
        return QueueBuilder.durable(PRICE_REQUEST_QUEUE)
                .withArgument("x-dead-letter-exchange", "order.dlx")
                .withArgument("x-dead-letter-routing-key", "price.request.dlq")
                .build();
    }

    @Bean
    public Queue orderEventsQueue() {
        return QueueBuilder.durable(ORDER_EVENTS_QUEUE)
                .withArgument("x-dead-letter-exchange", "order.dlx")
                .withArgument("x-dead-letter-routing-key", "order.events.dlq")
                .build();
    }

    @Bean
    public Binding priceRequestBinding(Queue priceRequestQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(priceRequestQueue).to(orderExchange).with(PRICE_REQUEST_KEY);
    }

    @Bean
    public Binding orderEventsBinding(Queue orderEventsQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderEventsQueue).to(orderExchange).with(ORDER_EVENTS_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
