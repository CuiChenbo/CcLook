package com.cuichen.cclook.fragment.ha

import android.view.Gravity
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuichen.common.base.BaseCacheFragment
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.cclook.R
import com.cuichen.cclook.abapter.QiuBaiTextAdapter
import com.cuichen.cclook.bean.QiuBaiBean
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.fragment_ha_img.*

/**
 * A simple [Fragment] subclass.
 */
class TextFragment : BaseCacheFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_ha_text
    }

    lateinit var mAdapter : QiuBaiTextAdapter
    override fun initView() {
        rv.layoutManager = LinearLayoutManager(mContext , RecyclerView.VERTICAL , false)
        mAdapter  = QiuBaiTextAdapter(R.layout.item_qiubai_text)
        rv.adapter = mAdapter
    }
    override fun initData() {
        loadData(true)
    }

    var page = 1
    fun loadData(dialog : Boolean) {

    }
    override fun initListener() {
        refreshLayout.setOnRefreshListener {
            page =1
            loadData(true)
        }
        refreshLayout.setOnLoadMoreListener {
            page ++
            loadData(false)
        }
    }
}