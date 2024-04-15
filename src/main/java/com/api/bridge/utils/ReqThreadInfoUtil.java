package com.api.bridge.utils;

public class ReqThreadInfoUtil {
    private static final ThreadLocal<String> tokenMap = new ThreadLocal<>();

    public static String getToken(){
        return tokenMap.get();
    }

    public static void setToken(String token){
        tokenMap.set(token);
    }

    public static void removeToken(){
        tokenMap.remove();
    }
}
