package com.cuichen.cclook.fragment.ha

import android.view.Gravity
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuichen.common.base.BaseCacheFragment
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.cclook.R
import com.cuichen.cclook.abapter.QiuBaiImgAdapter
import com.cuichen.cclook.bean.QiuBaiImgBean
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.fragment_ha_img.*

/**
 * A simple [Fragment] subclass.
 */
class ImgFragment : BaseCacheFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_ha_img
    }

    lateinit var mAdapter : QiuBaiImgAdapter
    override fun initView() {
        rv.layoutManager = LinearLayoutManager(mContext , RecyclerView.VERTICAL , false)
        mAdapter  = QiuBaiImgAdapter(R.layout.item_qiubai_img)
        rv.adapter = mAdapter
    }
    override fun initData() {
        loadData(true)
    }

    var page = 1
    private fun loadData(dialog : Boolean) {

    }

    override fun initListener() {
      refreshLayout.setOnRefreshListener {
          page =1
          loadData(true)
      }
        refreshLayout.setOnLoadMoreListener {
            page++
            loadData(false)
        }
    }
}