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
        val params = HttpParams()
        params.put("page", page)
        params.put("count", 12)
        okGet(okUrl.QiuBaiImgUrl,  params ,"IMG" , dialog)
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


    override fun OkonSuccess(body: String, t: Any) {
        if (t != "IMG") return
        try {
            refreshLayout.finishLoadMore()
            refreshLayout.finishRefresh()
            val datas: QiuBaiImgBean = GsonUtils.fromJson(body, QiuBaiImgBean::class.java)
            if (datas.getItems() != null || datas.getItems().size != 0) {
                if (page == 1){
                    mAdapter.setNewInstance(datas.items)
                }else {
                    mAdapter.addData(datas.getItems())
                }
            }
        } catch (e: Exception) {
            refreshLayout.finishLoadMore(false)
            refreshLayout.finishRefresh(false)
        }
    }

    override fun OkonError(response: Response<String>?) {
        super.OkonError(response)
        val tv = TextView(activity)
        tv.gravity = Gravity.CENTER
        tv.text = "卧槽,加载失败了"
        mAdapter.setEmptyView(tv)
    }
}