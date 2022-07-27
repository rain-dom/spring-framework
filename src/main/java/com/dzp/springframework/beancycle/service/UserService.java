package com.dzp.springframework.beancycle.service;

import com.dzp.springframework.beancycle.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser();

    String doMethod2();
    String doMethod3( ) throws Exception;

}
