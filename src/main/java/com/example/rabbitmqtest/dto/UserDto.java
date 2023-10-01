package com.example.rabbitmqtest.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
