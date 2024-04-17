package com.api.bridge.utils;

import com.alibaba.fastjson2.JSONObject;
import com.api.bridge.dao.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class UserHelperUtil {

    public static User getUser(){
        try {
            String encrypt = SecretUtil.encrypt(ReqThreadInfoUtil.getToken());
            return JSONObject.parseObject(encrypt,User.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }
}
