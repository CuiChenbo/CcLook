package com.cuichen.cclook.mvp

import com.cuichen.cclook.bean.LoginBean
import com.cuichen.common.base.BaseApplication
import com.cuichen.common.base.BaseConst
import com.cuichen.common.bean.ResultBean
import com.cuichen.common.bean.user.UserInfoBean
import com.cuichen.common.eventbus.LoginState
import com.cuichen.common.http.HttpUtils
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

class MyPresenter : BasePresenter<MyView?> {

    var view : MyView? = null
    override fun attach(view: MyView?) {
        this.view = view
    }

     fun goLogout(){
         view?.showLoadView()
         OkGo.post<String>(okUrl.LOGOUT)
             .execute(object : StringCallback() {
                 override fun onSuccess(response: Response<String>) {
                     response.body()?.let {
                         val result = GsonUtils.fromJson(it , ResultBean::class.java)
                         if(result.errorCode == 0){
                             BaseApplication.get().userInfo = UserInfoBean()
                             HttpUtils.saveCookies("")
                             BaseApplication.get().okGoCookieHeader("")
                             PreferenceUtils.put(BaseConst.USER_INFO_SP , "")
                             EventBus.getDefault().post(LoginState(false))
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

    fun collectList(){
         view?.showLoadView()
         OkGo.post<String>(okUrl.COLLECT_LIST)
             .execute(object : StringCallback() {
                 override fun onSuccess(response: Response<String>) {
                     response.body()?.let {
                         val result = GsonUtils.fromJson(it , ResultBean::class.java)
                         if(result.errorCode == 0){

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