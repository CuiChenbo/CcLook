package com.cuichen.common.utils;

import com.google.gson.Gson;

public class GsonUtils {

    private volatile static Gson gson;

    static {
        if (gson == null)
            gson = new Gson();
    }

    public static <T> T fromJson(String json , Class<T> clazz){
        synchronized (GsonUtils.class){
            return gson.fromJson(json , clazz);
        }
    }

    public static String toJson(Object object){
        synchronized (GsonUtils.class){
            return gson.toJson(object);
        }
    }

}
