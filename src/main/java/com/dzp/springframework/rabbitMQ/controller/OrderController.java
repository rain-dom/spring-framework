package com.dzp.springframework.rabbitMQ.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;



}
