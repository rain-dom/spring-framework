package com.dzp.springframework.beanCircle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "uu", initMethod = "doInit", destroyMethod = "doDestroy")
    public User create() {
        User user = new User();
        user.setName("chris");
        user.setAge(18);
        return user;
    }

}
