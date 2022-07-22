package com.dzp.springframework.rabbitMQ;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
public class LazyController {

    private static String str = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(str);


}
