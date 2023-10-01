package com.example.rabbitmqtest.cofig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;

    @Value("${spring.rabbitmq.json.queue.name}")
    private String jsonTestQueueName;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Value("${spring.rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    @Bean
    public Queue testQueue(){
            return new Queue(queueName,true);
    }

    @Bean
    public Queue jsonTestQueue(){
        return new Queue(jsonTestQueueName, true);
    }

    /*
    * Create json queue
    * Bind the queue to exchange
    * Configure message converter
    * configure amqp
    * */

    @Bean
    public TopicExchange testExchange(){
        return new TopicExchange(exchangeName,true,false);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding bindingBuilder(){
        return BindingBuilder
                .bind(testQueue())
                .to(testExchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonTestQueue())
                .to(testExchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
