package com.cuichen.cclook.mvp

import com.cuichen.cclook.bean.LoginBean
import com.cuichen.common.base.BaseApplication
import com.cuichen.common.base.BaseConst
import com.cuichen.common.bean.user.UserInfoBean
import com.cuichen.common.eventbus.LoginState
import com.cuichen.common.http.okUrl
import com.cuichen.common.mvp.BasePresenter
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.PreferenceUtils
import com.cuichen.common.utils.TU
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import org.greenrobot.eventbus.EventBus

class LoginPresenter : BasePresenter<LoginView?> {

    var view : LoginView? = null
    override fun attach(view: LoginView?) {
        this.view = view
    }

     fun goLogin(username:String , password : String){
         val pa =  HttpParams()
         pa.put("username" ,username )
         pa.put("password" ,password )
         view?.showLoadView()
         OkGo.post<String>(okUrl.LOGIN)
             .params(pa)
             .execute(object : StringCallback() {
                 override fun onSuccess(response: Response<String>) {
                     response.body()?.let {
                         var result = GsonUtils.fromJson(it , LoginBean::class.java)
                         if (result.errorCode == 0) {
                             BaseApplication.get().userInfo = result.data?: UserInfoBean()
                             PreferenceUtils.put(BaseConst.USER_INFO_SP , GsonUtils.toJson(result.data))
                             EventBus.getDefault().post(LoginState(true))
                             view?.onResult("0")
                         } else {
                             TU.showToast(result.errorMsg)
                         }
                     }
                 }

                 override fun onFinish() {
                     super.onFinish()
                     view?.dismissLoadView()
                 }
             })
     }

    override fun detach() {
        view = null
    }
}