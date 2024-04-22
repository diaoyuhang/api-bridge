package com.api.bridge.service.impl;

import com.api.bridge.dao.UserDao;
import com.api.bridge.dao.domain.User;
import com.api.bridge.dto.user.UserReqDto;
import com.api.bridge.service.UserService;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User register(User user) {
        Assert.isTrue(userDao.selectCountByEmail(user.getEmail()) < 1, user.getEmail() + " 已经存在");

        user.setCreateTime(new Date());
        user.setCreator(user.getEmail());
        user.setEditTime(new Date());
        user.setEditor(user.getEmail());
        userDao.insert(user);
        return user;
    }

    @Override
    public User login(User user) {
        User oldUser = userDao.selectByEmailAndPassword(user);
        Assert.isTrue(oldUser != null, "用户名或密码错误");
        Assert.isTrue(oldUser.getStatus().equals(User.ACTIVATED_STATUS),"账号不是激活状态");
        return oldUser;
    }

    @Override
    public User editInfo(UserReqDto userReqDto) {
        User oldUser = userDao.selectByEmailAndPassword(userReqDto.convertUser());
        Assert.isTrue(oldUser != null, "密码错误");
        Assert.isTrue(oldUser.getStatus().equals(User.ACTIVATED_STATUS),"账号不是激活状态");

        oldUser.setPassword(SecretUtil.encrypt(userReqDto.getNewPassword()));
        oldUser.setName(userReqDto.getName());
        UserHelperUtil.fillEditInfo(oldUser);
        userDao.updateByPrimaryKey(oldUser);
        return oldUser;
    }
}
