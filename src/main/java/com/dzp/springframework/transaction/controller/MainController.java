package com.dzp.springframework.transaction.controller;

import com.dzp.springframework.transaction.entity.User;
import com.dzp.springframework.transaction.service.impl.User1ServiceImpl;
import com.dzp.springframework.transaction.service.impl.User2ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    User1ServiceImpl userService1;

    @Autowired
    User2ServiceImpl userService2;


    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.notransaction_required();
    }

    /**
     * 外围事务
     */
    @GetMapping("/required")
    @Transactional(propagation = Propagation.REQUIRED)
    public void notransaction_required() {
        User user1 = new User(1, "LISA", 18, "GAMIL");
        userService1.addRequired(user1);

        User user2 = new User(2, "LISA", 18, "GAMIL");
        userService2.addRequired(user2);

    }


}
