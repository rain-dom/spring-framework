package com.dzp.springframework.transaction.service.impl;


import com.dzp.springframework.transaction.dao.UserDao;
import com.dzp.springframework.transaction.entity.User;
import com.dzp.springframework.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class User1ServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequired(User user1) {
        userDao.updateByPrimaryKey(user1);
    }
}
