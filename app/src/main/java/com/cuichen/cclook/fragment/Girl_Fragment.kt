package com.cuichen.cclook.fragment


import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cuichen.common.base.BaseFragment
import com.cuichen.common.http.okUrl
import com.cuichen.common.view.UiUtils
import com.cuichen.cclook.R
import com.cuichen.cclook.abapter.GirlAdapter
import com.cuichen.cclook.bean.GankGirlV2Bean
import com.cuichen.cclook.bean.HomeBannerBean
import com.google.gson.Gson
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.fragment_girl.*


/**
 * CCB simple [Fragment] subclass.
 *
 */
class Girl_Fragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_girl
    }

    private lateinit var mAdapter : GirlAdapter
    lateinit var headView : View
    lateinit var banner: Banner<HomeBannerBean.DataBean, BannerImageAdapter<HomeBannerBean.DataBean>>
    override fun initView() {
        val rlm = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
        rv.layoutManager = (rlm)
        mAdapter = GirlAdapter(R.layout.item_girl_l)
        rv.adapter = mAdapter

    }

    var page = 1
    override fun initData() {
        loadData(true)
    }

    fun loadData(dialog : Boolean){
    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener { page = 1
            initData()}
        refreshLayout.setOnLoadMoreListener { page++
        loadData(false)}
    }



}
