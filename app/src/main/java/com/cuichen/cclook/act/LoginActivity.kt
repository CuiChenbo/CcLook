package com.cuichen.cclook.act

import com.cuichen.common.base.BaseActivity
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.TU
import com.cuichen.cclook.R
import com.cuichen.cclook.bean.LoginBean
import com.lzy.okgo.model.HttpParams
import kotlinx.android.synthetic.main.activity_login.*


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
               finish()
           } else
               TU.showToast(result.errorMsg)
       }
   }

}
