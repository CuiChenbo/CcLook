package com.cuichen.cclook.fragment


import androidx.fragment.app.Fragment
import com.cuichen.common.base.BaseFragment
import com.cuichen.cclook.R
import kotlinx.android.synthetic.main.fragment_my_.*


/**
 * CCB simple [Fragment] subclass.
 *
 */
class My_Fragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_my_
    }

    override fun initView() {
        tv.setText("我的鸭")
    }

    override fun initData() {
    }

    override fun initListener() {
    }


}
