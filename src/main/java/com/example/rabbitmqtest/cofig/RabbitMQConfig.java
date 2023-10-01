package com.example.rabbitmqtest.cofig;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;


    @Bean
    public Queue testQueue(){
            return new Queue(queueName,true);
    }

    @Bean
    public TopicExchange testExchange(){
        return new TopicExchange(exchangeName,true,false);
    }

    @Bean
    public Binding bindingBuilder(){
        return BindingBuilder
                .bind(testQueue())
                .to(testExchange())
                .with(routingKey);
    }
}
