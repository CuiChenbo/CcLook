package com.cuichen.cclook.act

import android.util.Log
import com.cuichen.common.base.BaseActivity
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.TU
import com.cuichen.cclook.R
import com.cuichen.cclook.bean.LoginBean
import com.cuichen.cclook.mvp.LoginPresenter
import com.cuichen.cclook.mvp.LoginView
import com.cuichen.common.base.BaseApplication
import com.cuichen.common.base.BaseConst
import com.cuichen.common.bean.ResultBean
import com.cuichen.common.bean.user.UserInfoBean
import com.cuichen.common.eventbus.LoginState
import com.cuichen.common.utils.PreferenceUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus


class LoginActivity : BaseActivity() , LoginView{
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    var  mp : LoginPresenter = LoginPresenter()
    override fun initView() {
        mp.attach(this)
    }

    override fun initData() {
    }

    override fun initListener() {
        goRegister.setOnClickListener { startAct(RegisterActivity::class.java) }

        login.setOnClickListener {
          mp.goLogin(username.text.toString() , password.text.toString())
        }
    }

    override fun onResult(result: String?) {
            finish()
    }

    override fun showLoadView() {
        setLoadingShow(true)
    }

    override fun dismissLoadView() {
        setLoadingShow(false)
    }

    override fun showLoadErrorView() {

    }

    override fun onDestroy() {
        super.onDestroy()
       mp.detach()
    }

}
