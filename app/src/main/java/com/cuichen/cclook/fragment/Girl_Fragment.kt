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

//        headView = LayoutInflater.from(activity).inflate(R.layout.banner_home , null, false)
//        banner = headView.findViewById(R.id.banner)
//        mAdapter.headerViewAsFlow = true
//        mAdapter.addHeaderView(headView)
    }

    var page = 1
    override fun initData() {
//        okGet(okUrl.GIRL_BANNER, null , "banner")
        loadData(true)
    }

    fun loadData(dialog : Boolean){
        okGet(okUrl.Girl + "page/$page/count/16", null , "girl" , dialog)
    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener { page = 1
            initData()}
        refreshLayout.setOnLoadMoreListener { page++
        loadData(false)}
    }

    override fun OkonSuccess(body: String, tag: Any) {
        super.OkonSuccess(body, tag)
        if (tag == "girl") {
            if (page == 1){
                refreshLayout.finishRefresh()
            }else{
                refreshLayout.finishLoadMore()
            }
            try {
                val datas: GankGirlV2Bean = Gson().fromJson(body, GankGirlV2Bean::class.java)
                if (datas.data != null) {
//                  for (index in datas.data.indices){
//                      datas.data[index].isLeft = index % 2 == 0
//                  }
                    if (page == 1){
                        datas.data[0].viewHeight = UiUtils.dp2px(210 * 1.3f)
                        mAdapter.setNewInstance(datas.data)
                    }else {
                        mAdapter.addData(datas.data)
                    }
                }
            } catch (e: Exception) {
                UiUtils.showToast(activity , "error")
                return
            }
        }else if (tag == "banner"){
//            val datas: HomeBannerBean = Gson().fromJson(body, HomeBannerBean::class.java)
//            setBanner(datas.data)
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
                    .load(data.image)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                    .into(holder.imageView)
            }
        }).addBannerLifecycleObserver(activity)
            .removeIndicator()
            .setLoopTime(5600)
            .setScrollTime(1000)

    }


}
