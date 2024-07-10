package com.cuichen.cclook.act

import com.cuichen.common.base.BaseActivity
import com.cuichen.common.bean.ResultBean
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.TU
import com.cuichen.cclook.R
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
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
            OkGo.post<String>(okUrl.REGISTER)
                .params(pa)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>?) {
                        response?.body()?.let { OkonSuccess(it) }
                    }

                    override fun onFinish() {
                        super.onFinish()
                        setLoadingShow(false)
                    }
                })
        }
    }

//    200 OK https://www.wanandroid.com/user/register (1329ms）
//    I/OkGo: 	Server: Apache-Coyote/1.1
//    I/OkGo: 	Set-Cookie: JSESSIONID=86C1A4E74431165509B86DC33A603251; Path=/; Secure; HttpOnly
//    I/OkGo: 	Set-Cookie: loginUserName=CCB1; Expires=Sat, 20-Apr-2024 05:50:39 GMT; Path=/
//    I/OkGo: 	Set-Cookie: token_pass=665b7de493386976de9b62ad3d10a7bd; Expires=Sat, 20-Apr-2024 05:50:39 GMT; Path=/
//    I/OkGo: 	Set-Cookie: loginUserName_wanandroid_com=CCB1; Domain=wanandroid.com; Expires=Sat, 20-Apr-2024 05:50:39 GMT; Path=/
//    I/OkGo: 	Set-Cookie: token_pass_wanandroid_com=665b7de493386976de9b62ad3d10a7bd; Domain=wanandroid.com; Expires=Sat, 20-Apr-2024 05:50:39 GMT; Path=/
//    I/OkGo: 	Content-Type: application/json;charset=UTF-8
//    I/OkGo: 	Transfer-Encoding: chunked
//    I/OkGo: 	Date: Thu, 21 Mar 2024 05:50:39 GMT
//    I/OkGo:
//    I/OkGo: 	body:{"data":{"admin":false,"chapterTops":[],"coinCount":0,"collectIds":[],"email":"","icon":"","id":159807,"nickname":"CCB1","password":"","publicName":"CCB1","token":"","type":0,"username":"CCB1"},"errorCode":0,"errorMsg":""}

   fun OkonSuccess(body : String){
           var result = GsonUtils.fromJson(body, ResultBean::class.java)
           if (result.errorCode == 0) {
               finish()
           } else
               TU.showToast(result.errorMsg)
       }


}
