package com.cuichen.common.http

import com.cuichen.common.base.BaseApplication
import okhttp3.Interceptor
import okhttp3.Response

class SaveCookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val url = request.url().toString()

        if((url.contains(HttpUtils.SAVE_COOKIE_USER_LOGIN) || url.contains(HttpUtils.SAVE_COOKIE_USER_REGISTER))
            && response.headers(HttpUtils.SET_COOKIE).isNotEmpty()){
         //保存登录后的cookie
            val saveCookie = HttpUtils.encodeCookie(response.headers(HttpUtils.SET_COOKIE))
            HttpUtils.saveCookies(saveCookie)
            BaseApplication.get().okGoCookieHeader(saveCookie)
        }
        return response
    }
}