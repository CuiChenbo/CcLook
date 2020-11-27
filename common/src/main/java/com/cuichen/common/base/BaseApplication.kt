package com.cuichen.common.base

import android.app.Application
import android.content.Context
import com.cuichen.common.utils.NineImageLoader
import com.lzy.ninegrid.NineGridView
import com.lzy.okgo.OkGo
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator

class BaseApplication : Application() {

    companion object {

       private var app: BaseApplication? = null

        fun get(): BaseApplication? {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        OkGo.getInstance().init(this)
        setSmartRefreshLayout()
        NineGridView.setImageLoader(NineImageLoader())
    }

    private fun setSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                return ClassicsHeader(context).setEnableLastTime(false)
            }
        })
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                return ClassicsFooter(context)
            }
        })
    }
}