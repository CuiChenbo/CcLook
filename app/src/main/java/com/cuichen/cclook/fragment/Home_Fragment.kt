package com.cuichen.cclook.fragment


import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cuichen.common.base.BaseFragment
import com.cuichen.common.http.okUrl
import com.cuichen.common.utils.GsonUtils
import com.cuichen.cclook.R
import com.cuichen.cclook.abapter.HomeArticleAdapter
import com.cuichen.cclook.bean.HomeArticleBean
import com.cuichen.cclook.bean.HomeBannerBean
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home_.*


/**
 * CCB simple [Fragment] subclass.
 *
 */
class Home_Fragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_home_
    }

    var mArticleAdapter = HomeArticleAdapter(R.layout.item_article)
    lateinit var headView : View
   lateinit var banner: Banner<HomeBannerBean.DataBean,BannerImageAdapter<HomeBannerBean.DataBean>>
    override fun initView() {
        refreshLayout.setEnableLoadMore(true)
        rv.layoutManager = LinearLayoutManager(activity , RecyclerView.VERTICAL, false)
        rv.adapter = mArticleAdapter

        headView = LayoutInflater.from(activity).inflate(R.layout.banner_home , null, false)
        banner = headView.findViewById(R.id.banner)
        mArticleAdapter.addHeaderView(headView)
        banner.addBannerLifecycleObserver(activity)

    }


    var pageNumber = 0
    override fun initData() {
      loadData(0 , true)
        okGet(okUrl.BANNER , null , "Banner" , false)
    }

    // 0是刷新 ， 1是加载
    fun loadData(isRefresh : Int , dialog : Boolean){
        okGet(okUrl.ARTICLE_LIST +"/$pageNumber/json", null , "ARTICLE_LIST$isRefresh" , dialog)
    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener {
            pageNumber = 0
            loadData(0 , true)
        }

        refreshLayout.setOnLoadMoreListener {
            pageNumber++
            loadData(1 , false)
        }


    }

    override fun OkonSuccess(body: String, tag: Any) {
        super.OkonSuccess(body, tag)
        if (tag == "ARTICLE_LIST0"){
            var homeArticleBean = GsonUtils.fromJson(body , HomeArticleBean::class.java)
            mArticleAdapter.setNewInstance(homeArticleBean.data.datas)
            refreshLayout.finishRefresh()
        }else if (tag == "ARTICLE_LIST1"){
            var homeArticleBean = GsonUtils.fromJson(body , HomeArticleBean::class.java)
            mArticleAdapter.addData(homeArticleBean.data.datas)
            refreshLayout.finishLoadMore()
        }else if (tag == "Banner"){
            var homeBannerBean = GsonUtils.fromJson(body , HomeBannerBean::class.java)
            homeBannerBean.data?.let { setBanner(it) }
        }
    }


    fun setBanner(datas : List<HomeBannerBean.DataBean>){
        banner.setAdapter(object : BannerImageAdapter<HomeBannerBean.DataBean>(datas) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: HomeBannerBean.DataBean,
                position: Int,
                size: Int
            ) { //图片加载自己实现
                Glide.with(holder.itemView)
                    .load(data.imagePath)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        })
            .addBannerLifecycleObserver(this).indicator = CircleIndicator(activity)
    }



}
