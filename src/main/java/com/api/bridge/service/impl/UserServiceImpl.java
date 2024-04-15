package com.api.bridge.service.impl;

import com.api.bridge.dao.UserDao;
import com.api.bridge.dao.domain.User;
import com.api.bridge.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User register(User user) {
        userDao.insert(user);
        return user;
    }
}
