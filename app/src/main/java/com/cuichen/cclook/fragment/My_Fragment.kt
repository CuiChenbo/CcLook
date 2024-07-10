package com.cuichen.cclook.fragment


import androidx.fragment.app.Fragment
import com.cuichen.common.base.BaseFragment
import com.cuichen.cclook.R
import com.cuichen.cclook.act.LoginActivity
import com.cuichen.cclook.mvp.MyPresenter
import com.cuichen.cclook.mvp.MyView
import com.cuichen.common.base.BaseApplication
import com.cuichen.common.base.BaseConst
import com.cuichen.common.bean.ResultBean
import com.cuichen.common.bean.user.UserInfoBean
import com.cuichen.common.eventbus.LoginState
import com.cuichen.common.http.HttpUtils
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.common.utils.PreferenceUtils
import com.lzy.okgo.OkGo
import kotlinx.android.synthetic.main.fragment_my_.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * CCB simple [Fragment] subclass.
 *
 */
class My_Fragment : BaseFragment() , MyView{


    override fun getLayoutId(): Int {
        return R.layout.fragment_my_
    }

    val mp = MyPresenter()

    override fun initView() {
        EventBus.getDefault().register(this)
        mp.attach(this)
    }

    override fun initData() {

        setUserInfo()
    }

   fun setUserInfo(){
       val ui = BaseApplication.get().userInfo
        tv_name.setText(ui.nickname)
       tv_lev.setText("积分${ui.coinCount}")
    }

    override fun initListener() {
        iv_user.setOnClickListener {
            startAct(LoginActivity::class.java)
        }
        iv_msg.setOnClickListener{

        }
        tv_outLogin.setOnClickListener {
           mp.goLogout()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMsg(loginState: LoginState){
      setUserInfo()
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        mp.detach()
    }

    override fun onResult(result: String?) {

    }

    override fun showLoadView() {
        setLoadingShow(true)
    }

    override fun dismissLoadView() {
        setLoadingShow(false)
    }

    override fun showLoadErrorView() {
    }


}
