package com.cuichen.common.http

import com.cuichen.common.utils.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response

class HeadresInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val host = request.url().host()
        builder.addHeader("Content-type", "application/json; charset=utf-8")
       val cookie = PreferenceUtils.get(host,"")
        if(host.isNotEmpty() && cookie is String){
            builder.addHeader(HttpUtils.COOKIE , cookie)
        }

        return chain.proceed(builder.build())
    }
}