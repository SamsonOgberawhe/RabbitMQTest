package com.example.rabbitmqtest;

import com.example.rabbitmqtest.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class Controller {

    private Producer producer;

    @Autowired
    public Controller(Producer producer){
        this.producer = producer;
    }

    @GetMapping("publish")
    public String sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return "Message sent successfully";
    }

}
