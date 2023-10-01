package com.example.rabbitmqtest.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SimpleProducer {

    private String queue;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    private RabbitTemplate rabbitTemplate;

    public SimpleProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        logger.info("Message Sent ->  {}", message);
        rabbitTemplate.convertAndSend(exchange,routingKey, message);
    }
}
