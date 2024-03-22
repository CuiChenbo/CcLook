package com.cuichen.common.utils

import android.content.Context
import android.text.TextUtils
import com.cuichen.common.base.BaseApplication


/**
 * SharedPreference 工具类
 */
object PreferenceUtils {
    /**
     * 保存在手机里面的文件名
     */
    const val FILE_NAME = "cclook_preference_data"

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    fun put( key: String?, any: Any?) {
        key ?: return
        val sp = BaseApplication.get().getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        when (any) {
            is String -> {
                editor.putString(key, any)
            }
            is Int -> {
                editor.putInt(key, any)
            }
            is Boolean -> {
                editor.putBoolean(key, any)
            }
            is Float -> {
                editor.putFloat(key, any)
            }
            is Long -> {
                editor.putLong(key, any)
            }
            else -> {
                editor.putString(key, any.toString())
            }
        }
        editor.apply()
    }

    fun get(k : String?) : String{
        if(TextUtils.isEmpty(k))return ""
        return get(k , "") as String
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    fun get( key: String?, defaultObject: Any?): Any? {
        val sp = BaseApplication.get().getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        when (defaultObject) {
            is String -> {
                return sp.getString(key, defaultObject as String?)
            }
            is Int -> {
                return sp.getInt(key, (defaultObject as Int?)!!)
            }
            is Boolean -> {
                return sp.getBoolean(key, (defaultObject as Boolean?)!!)
            }
            is Float -> {
                return sp.getFloat(key, (defaultObject as Float?)!!)
            }
            is Long -> {
                return sp.getLong(key, (defaultObject as Long?)!!)
            }
            else -> return null
        }
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    fun remove( key: String?) {
        val sp = BaseApplication.get().getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    fun clear() {
        val sp = BaseApplication.get().getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    fun contains( key: String?): Boolean {
        val sp = BaseApplication.get().getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.contains(key)
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    fun getAll(): Map<String, *> {
        val sp = BaseApplication.get().getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        return sp.all
    }
    
}