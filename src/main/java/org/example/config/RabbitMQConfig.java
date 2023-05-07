package org.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String DELAY_EXCHANGE_NAME = "delay.exchange";
    public static final String DELAY_QUEUE_NAME = "delay.queue";
    public static final String DELAY_ROUTING_KEY = "delay.routingKey";

    @Bean
    public Exchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE_NAME, "x-delayed-message", true, false, args);

    }

    @Bean
    public Queue delayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new Queue(DELAY_QUEUE_NAME, true, false, false, arguments);
    }

    @Bean
    public Binding delayBinding(Exchange delayExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(DELAY_ROUTING_KEY).noargs();
    }
}
