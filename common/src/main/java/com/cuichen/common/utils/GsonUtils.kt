package com.cuichen.common.utils

import com.cuichen.common.bean.ResultBean
import com.google.gson.Gson

object GsonUtils {
    @Volatile
    private var gson: Gson? = null

    init {
        if (gson == null) gson = Gson()
    }

    fun <T> fromJson(json: String?, clazz: Class<T>?): T {
        synchronized(GsonUtils::class.java) {
            try {
                return gson!!.fromJson(json, clazz)
            } catch (e: Exception) {
                e.fillInStackTrace()
                return ResultBean<T>() as T
            }
        }
    }

    fun toJson(`object`: Any?): String {
        synchronized(GsonUtils::class.java) {
            return gson!!.toJson(`object`)
        }
    }
}
