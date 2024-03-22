package com.cuichen.cclook.act

import android.util.Log
import com.cuichen.common.base.BaseActivity
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.TU
import com.cuichen.cclook.R
import com.cuichen.cclook.bean.LoginBean
import com.cuichen.common.base.BaseApplication
import com.cuichen.common.base.BaseConst
import com.cuichen.common.bean.ResultBean
import com.cuichen.common.bean.user.UserInfoBean
import com.cuichen.common.eventbus.LoginState
import com.cuichen.common.utils.PreferenceUtils
import com.lzy.okgo.model.HttpParams
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus


class LoginActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
        goRegister.setOnClickListener { startAct(RegisterActivity::class.java) }

        login.setOnClickListener {
            val pa =  HttpParams()
            pa.put("username" ,username.text.toString() )
            pa.put("password" ,password.text.toString() )
            okPost(okUrl.LOGIN , pa , this)
        }
    }

   override fun OkonSuccess(body : String, tag : Any){
       if (tag == this){
          var result = GsonUtils.fromJson(body , LoginBean::class.java)
           if (result.errorCode == 0) {
               BaseApplication.get().usreInfo = result.data?:UserInfoBean()
               PreferenceUtils.put(BaseConst.USER_INFO_SP , result.data.toString())
               EventBus.getDefault().post(LoginState(true))
               finish()
           } else
               TU.showToast(result.errorMsg)
       }
   }

}
