package com.cuichen.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import com.cuichen.common.R
import kotlinx.android.synthetic.main.base_actview.*

/**
 * 自带缓存View的Fragment基类，适用于ViewPager+Fragment
 */
abstract class BaseCacheFragment : BaseFragment() {
    private var isAttached = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) rootView = inflater.inflate(R.layout.base_actview, container, false)
        val parent = rootView!!.parent
        if (parent != null){
            parent as ViewGroup
            parent.removeView(rootView)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!isAttached) {
            inflate(activity, getLayoutId(), contentView)
            initView()
            initData()
            initListener()
            isAttached = true
        }
    }

    fun onRefresh() {}
}