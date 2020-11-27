package com.cuichen.cclook.act

import com.cuichen.common.base.BaseActivity
import com.cuichen.common.bean.ResultBean
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.TU
import com.cuichen.cclook.R
import com.lzy.okgo.model.HttpParams
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {

        register.setOnClickListener {
            val pa =  HttpParams()
            pa.put("username" ,username.text.toString() )
            pa.put("password" ,password.text.toString() )
            pa.put("repassword" ,password2.text.toString() )
            okPost(okUrl.REGISTER , pa , this)
        }
    }

   override fun OkonSuccess(body : String, tag : Any){
       if (tag == this) {
           var result = GsonUtils.fromJson(body, ResultBean::class.java)
           if (result.errorCode == 0) {
               finish()
           } else
               TU.showToast(result.errorMsg)
       }
       }

}
