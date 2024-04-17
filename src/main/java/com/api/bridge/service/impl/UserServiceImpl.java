package com.api.bridge.service.impl;

import com.api.bridge.dao.UserDao;
import com.api.bridge.dao.domain.User;
import com.api.bridge.service.UserService;
import com.api.bridge.utils.UserHelperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User register(User user) {
        user.setCreateTime(new Date());
        user.setCreator(user.getEmail());
        user.setEditTime(new Date());
        user.setEditor(user.getEmail());
        userDao.insert(user);
        return user;
    }
}
