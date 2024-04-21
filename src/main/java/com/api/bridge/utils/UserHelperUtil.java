package com.api.bridge.utils;

import com.alibaba.fastjson2.JSONObject;
import com.api.bridge.dao.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class UserHelperUtil {
    private final static Logger logger = LoggerFactory.getLogger(UserHelperUtil.class);
    public static User getUser(){
        try {
            String encrypt = SecretUtil.encrypt(ReqThreadInfoUtil.getToken());
            return JSONObject.parseObject(encrypt,User.class);
        } catch (Exception e) {
            logger.error("getUser:"+e.getMessage(),e);
            throw new RuntimeException("获取用户信息失败");
        }
    }

    public static void fillCreateInfo(Object object)  {
        try {
            User user = getUser();
            Method setCreateTime = object.getClass().getMethod("setCreateTime");
            Method setCreator = object.getClass().getMethod("setCreator");

            setCreateTime.invoke(object,new Date());
            setCreator.invoke(object,user.getEmail());
        } catch (Exception e) {
            logger.error("fillCreateInfo:"+e.getMessage(),e);
            throw new RuntimeException("填充创建信息异常");
        }
    }

    public static void fillEditInfo(Object object)  {
        try {
            User user = getUser();
            Method setEditTime = object.getClass().getMethod("setEditTime");
            Method setEditor = object.getClass().getMethod("setEditor");

            setEditTime.invoke(object,new Date());
            setEditor.invoke(object,user.getEmail());
        } catch (Exception e) {
            logger.error("fillEditInfo:"+e.getMessage(),e);
            throw new RuntimeException("填充编辑信息异常");
        }
    }
}
