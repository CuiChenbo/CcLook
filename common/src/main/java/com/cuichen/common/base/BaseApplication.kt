package com.cuichen.common.base

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.cuichen.common.bean.user.UserInfoBean
import com.cuichen.common.http.HeadresInterceptor
import com.cuichen.common.http.HttpUtils
import com.cuichen.common.http.SaveCookieInterceptor
import com.cuichen.common.utils.NineImageLoader
import com.cuichen.common.utils.PreferenceUtils
import com.lzy.ninegrid.NineGridView
import com.lzy.okgo.OkGo
import com.lzy.okgo.https.HttpsUtils
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.lzy.okgo.model.HttpHeaders
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

class BaseApplication : Application() {

    companion object {

       private lateinit var app: BaseApplication

        fun get(): BaseApplication {
            return app
        }
    }

    var usreInfo = UserInfoBean()

    override fun onCreate() {
        super.onCreate()
        app = this
        OkGo.getInstance().init(this).let {
            it.okHttpClient = okHttpClient()
            it.addCommonHeaders(HttpHeaders("Content-type", "application/json; charset=utf-8"))
            val cookie = PreferenceUtils.get(HttpUtils.SAVE_COOKIE,"")
            if(cookie is String && !TextUtils.isEmpty(cookie)) {
                okGoCookieHeader(cookie)
            }
        }

        setSmartRefreshLayout()
        NineGridView.setImageLoader(NineImageLoader())
    }

    fun okGoCookieHeader(cookie : String){
        if(OkGo.getInstance().commonHeaders.headersMap.containsKey(HttpUtils.COOKIE)) {
            OkGo.getInstance().commonHeaders.remove(HttpUtils.COOKIE)
        }
        if(!TextUtils.isEmpty(cookie)) {
            OkGo.getInstance().addCommonHeaders(HttpHeaders(HttpUtils.COOKIE, cookie))
        }
    }

    private fun okHttpClient() : OkHttpClient{
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor("CcLook")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)
        builder.addInterceptor(loggingInterceptor)
        builder.addInterceptor(SaveCookieInterceptor())
//        builder.addInterceptor(HeadresInterceptor())

        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)

        val sslParams = HttpsUtils.getSslSocketFactory()
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier)
        return builder.build()
    }

    private fun setSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            ClassicsHeader(
                context
            ).setEnableLastTime(false)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(
                context
            )
        }
    }
}