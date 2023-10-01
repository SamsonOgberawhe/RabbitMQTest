package com.example.rabbitmqtest.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void consumeMessage(String message){
        logger.info("Message received -> {}", message);
    }
}
