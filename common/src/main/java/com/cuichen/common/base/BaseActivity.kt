package com.cuichen.common.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.widget.FrameLayout
import androidx.annotation.IntDef
import androidx.appcompat.app.AppCompatActivity
import com.cuichen.common.R
import com.cuichen.common.utils.StatusBarUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.base_actview.*
import kotlinx.android.synthetic.main.dialog_rootview.*

abstract class BaseActivity : AppCompatActivity() {

    var mConetxt : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_actview)
        LayoutInflater.from(this).inflate(getLayoutId(), contentView)
        mConetxt = this
        if (isTranslucent()){
            StatusBarUtil.setTransparentForWindow(this)
            StatusBarUtil.setDarkMode(this)
        }
        initView()
        initData()
        initListener()
    }

    abstract fun getLayoutId() : Int
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()
    protected fun isTranslucent() : Boolean{
        return false
    }


    open fun <T : Activity> startAct (cls : Class<T> ){
        startActivity(Intent(mConetxt , cls))
    }

    fun setLoadingShow(visibility : Boolean){
        loading.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
        loading_rootview.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }

    fun okPost(url : String  , params : HttpParams , tag : Any){

    }


    override fun onDestroy() {
        super.onDestroy()
        OkGo.cancelTag(null,this)
    }
}
