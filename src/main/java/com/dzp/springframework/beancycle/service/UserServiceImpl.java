package com.dzp.springframework.beancycle.service;

import com.dzp.springframework.beancycle.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        System.out.println("JdkProxyServiceImpl.doMethod1()");
        User user = new User("lisa", 18);
        return user;
    }

    @Override
    public String doMethod2() {
        System.out.println("JdkProxyServiceImpl.doMethod2()");
        return "hello world";
    }

    @Override
    public String doMethod3() throws Exception {
        System.out.println("JdkProxyServiceImpl.doMethod3()");
        throw new Exception("some exception");
    }

}
