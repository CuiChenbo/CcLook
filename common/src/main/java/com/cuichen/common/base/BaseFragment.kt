package com.cuichen.common.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cuichen.common.R
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.base_actview.*
import kotlinx.android.synthetic.main.dialog_rootview.*

abstract class BaseFragment : Fragment() {

    var rootView : View?? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.base_actview, container, false)
        mContext = activity
        return rootView
    }

   open var mContext : Context? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflate( activity, getLayoutId(), contentView)
        initView()
        initData()
        initListener()
    }

    abstract fun getLayoutId() : Int
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()


    open fun <T : Activity> startAct (cls : Class<T> ){
        startActivity(Intent(mContext , cls))
    }

    fun setLoadingShow(visibility : Boolean){
        loading.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
        loading_rootview.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }
}
