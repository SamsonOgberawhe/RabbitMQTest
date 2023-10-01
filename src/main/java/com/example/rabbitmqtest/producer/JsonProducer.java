package com.example.rabbitmqtest.producer;

import com.example.rabbitmqtest.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.json.routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(JsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public JsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(UserDto userDto){
        logger.info("Message Sent ->  {}", userDto.toString());
        rabbitTemplate.convertAndSend(exchange,routingKey, userDto);
    }


}
