package com.dzp.springframework.rabbitMQ.controller;

import com.rabbitmq.client.Channel;
import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.core.Message;

public class Lisenter {

    public void getMessage(Message msg, Channel channel) {
        String orderCode = new String(msg.getBody());
        System.out.println("超时订单： " + orderCode);

    }

}
