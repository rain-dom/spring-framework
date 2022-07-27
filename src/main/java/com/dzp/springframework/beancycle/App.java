package com.dzp.springframework.beancycle;

import com.dzp.springframework.beancycle.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class App {

    public static void main(String[] args) {

        log.info("Init application context");
        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.dzp.springframework.beancycle");

        // retrieve configured instance
        UserServiceImpl userServiceImpl = context.getBean("userServiceImpl", UserServiceImpl.class);
        User user = userServiceImpl.getUser();
        userServiceImpl.doMethod2();
        try {
            userServiceImpl.doMethod3();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // print info from beans
        log.info(user.toString());
        log.info("Shutdown application context");
        context.registerShutdownHook();
    }

}
