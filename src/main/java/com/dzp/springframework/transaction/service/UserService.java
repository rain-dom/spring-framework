package com.dzp.springframework.transaction.service;

import com.dzp.springframework.transaction.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void addRequired(User user1);
}
