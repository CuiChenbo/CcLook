package com.cuichen.common.http

import com.cuichen.common.utils.PreferenceUtils

object HttpUtils {
    const val  SAVE_COOKIE_USER_REGISTER = "user/register"
    const val  SAVE_COOKIE_USER_LOGIN = "user/login"
    const val  SET_COOKIE = "Set-Cookie"
    const val  COOKIE = "Cookie"
    const val  SAVE_COOKIE = "SAVE_COOKIE"


    fun encodeCookie(cookies : List<String>) : String{
        val sb = java.lang.StringBuilder()
        val set = HashSet<String>()

        cookies.forEach { c ->
            val cookie = c.split(";")
            cookie.forEach {
                if (!set.contains(it)){
                    set.add(it)
                }
            }
        }

        set.forEach {
            sb.append(it).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last && last != 0) {
            sb.deleteCharAt(last)
        }

        return sb.toString()
    }

    fun saveCookies(url:String? , host : String? , cookie : String?){
        url ?: return
        PreferenceUtils.put(url , cookie)

        host?: return
        PreferenceUtils.put(host , cookie)
    }

    fun saveCookies(cookie : String?){
        PreferenceUtils.put(SAVE_COOKIE , cookie)
    }
}