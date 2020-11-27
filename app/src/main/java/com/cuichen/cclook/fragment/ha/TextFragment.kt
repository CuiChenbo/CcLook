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
        loadData()
    }

    var page = 1
    fun loadData() {
        val params = HttpParams()
        params.put("type", "text")
        params.put("page", page)
        params.put("count", 16)
        okGet( okUrl.QiuBaiTextUrl, params , "T")
    }
    override fun initListener() {
        refreshLayout.setOnRefreshListener {
            page =1
            loadData()
        }
        refreshLayout.setOnLoadMoreListener {
            page ++
            loadData()
        }
    }

    override fun OkonSuccess(body: String, tag: Any) {
        super.OkonSuccess(body, tag)
        if (tag != "T") return
        refreshLayout.finishRefresh(true)
        refreshLayout.finishLoadMore(true)
        try {
            val datas: QiuBaiBean = GsonUtils.fromJson(body, QiuBaiBean::class.java)
            if (datas.getItems() != null && datas.getItems().size > 0) {
                mAdapter.addData(datas.getItems())
            }
        } catch (e: Exception) {
            e.fillInStackTrace()
           refreshLayout.finishRefresh(false)
           refreshLayout.finishLoadMore(false)
        }
    }

    override fun OkonError(response: Response<String>?) {
        super.OkonError(response)
        refreshLayout.finishRefresh(false)
        refreshLayout.finishLoadMore(false)
        val tv = TextView(activity)
        tv.gravity = Gravity.CENTER
        tv.text = "卧槽,加载失败了"
        mAdapter.setEmptyView(tv)
    }
}