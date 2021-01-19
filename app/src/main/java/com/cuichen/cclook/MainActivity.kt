package com.cuichen.cclook

import android.graphics.Typeface
import androidx.fragment.app.Fragment
import com.cuichen.common.base.BaseActivity
import com.cuichen.common.utils.L
import com.cuichen.common.view.BottomBar.BottomBarListener
import com.cuichen.common.view.BottomBar.BottomBarView
import com.cuichen.cclook.fragment.Ha_Fragment
import com.cuichen.cclook.fragment.Girl_Fragment
import com.cuichen.cclook.fragment.Home_Fragment
import com.cuichen.cclook.fragment.My_Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    val tabs = listOf("学点","段子","妹纸","我")
    override fun initView() {
        for(tab in tabs){
            var bottomBarView = BottomBarView(mConetxt)
            val tv = bottomBarView.textView
            tv.textSize = 14f
            tv.typeface = Typeface.DEFAULT_BOLD
            tv.text = tab
            navi.addItem(bottomBarView)
        }
        navi.setBarTextColor(R.color.color999 , R.color.color111)


    }

    private val HomeFm: Fragment = Home_Fragment()
    private val D2Fm: Fragment = Ha_Fragment()
    private val D3Fm: Fragment = Girl_Fragment()
    private val MyFm: Fragment = My_Fragment()


    override fun initData() {
    }


    override fun initListener() {

        navi.setBottomBarListener(object : BottomBarListener {
            override fun onSelectedBar(position: Int, barView: BottomBarView?) {
                L.c("选中$position")
                when (position) {
                    0 -> switchFragment(showFragment, HomeFm)
                    1 -> switchFragment(showFragment, D2Fm)
                    2 -> switchFragment(showFragment, D3Fm)
                    3 -> switchFragment(showFragment, MyFm)
                }
            }

            override fun onClickSelectedBar(position: Int, barView: BottomBarView?) {
                L.c("点击已选中$position")
            }

            override fun onCancelSelectedBar(position: Int, barView: BottomBarView?) {
                L.c("取消选中$position")
            }
        })
        navi.initialise()
    }


    private var showFragment: Fragment? = null  //当前显示的fragment

    fun switchFragment(hide: Fragment? , show: Fragment){
        val ft = supportFragmentManager.beginTransaction()
        if (hide == show)return
        if (hide != null){
            if (!show.isAdded){
                ft.hide(hide).add(R.id.fl, show).commit()
            }else{
                ft.hide(hide).show(show).commit()
            }
        }else {
            if (!show.isAdded){
                ft.add(R.id.fl, show).commit()
            }else{
                ft.show(show).commit()
            }
        }
        showFragment = show
    }

}
