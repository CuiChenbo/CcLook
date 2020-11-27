package com.cuichen.cclook.fragment


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cuichen.common.base.BaseFragment
import com.cuichen.cclook.R
import com.cuichen.cclook.fragment.ha.ImgFragment
import com.cuichen.cclook.fragment.ha.TextFragment
import kotlinx.android.synthetic.main.fragment_ha.*


/**
 * CCB simple [Fragment] subclass.
 *
 */
class Ha_Fragment : BaseFragment() {

    val tabs = listOf("图","文")
   open val frafments = listOf(ImgFragment() , TextFragment())

    override fun getLayoutId(): Int {
        return R.layout.fragment_ha
    }

    override fun initView() {
//        for (s in tabs){
//            tabLayout.addTab(tabLayout.newTab().setText(s))
//        }
        tabLayout.setupWithViewPager(vp)
        vp.adapter = FragAdapter(childFragmentManager)
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    inner class FragAdapter : FragmentPagerAdapter{
        constructor(fm : FragmentManager) : super(fm , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        }

        override fun getItem(position: Int): Fragment {
            return frafments[position]
        }

        override fun getCount(): Int {
            return frafments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabs[position]
        }

    }

}
