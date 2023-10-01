package com.example.rabbitmqtest;

import com.example.rabbitmqtest.dto.UserDto;
import com.example.rabbitmqtest.producer.JsonProducer;
import com.example.rabbitmqtest.producer.SimpleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class Controller {

    private SimpleProducer simpleProducer;

    private JsonProducer jsonProducer;

    @Autowired
    public Controller(SimpleProducer simpleProducer, JsonProducer jsonProducer){
        this.jsonProducer = jsonProducer;
        this.simpleProducer = simpleProducer;
    }

    @GetMapping("publish/simple")
    public String sendMessage(@RequestParam("message") String message){
        simpleProducer.sendMessage(message);
        return "Message sent successfully";
    }

    @PostMapping("publish/json")
    public String publishJson(@RequestBody UserDto userDto){
        jsonProducer.sendMessage(userDto);
        return "JSON Message sent successfully";
    }
}
