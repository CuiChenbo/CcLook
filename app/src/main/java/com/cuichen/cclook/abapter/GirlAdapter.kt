package com.cuichen.cclook.abapter

import android.widget.FrameLayout
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.cuichen.common.utils.GlideImageUtils
import com.cuichen.common.view.UiUtils
import com.cuichen.cclook.R
import com.cuichen.cclook.bean.GankGirlV2Bean

class GirlAdapter : BaseQuickAdapter<GankGirlV2Bean.DataBean, BaseViewHolder> {
    var defViewHeight = 0 //默认高度
    constructor(layoutResId: Int) : super(layoutResId) {
        defViewHeight = UiUtils.dp2px(210f)
    }

    override fun convert(holder: BaseViewHolder, item: GankGirlV2Bean.DataBean) {
        val iv: ImageView = holder.getView(R.id.iv)
        val layoutparams = iv.layoutParams as FrameLayout.LayoutParams
            if (item.viewHeight == 0){
              layoutparams.height = defViewHeight
            }else{
                layoutparams.height = item.viewHeight
            }
        iv.layoutParams = layoutparams
        GlideImageUtils.DisplayRoundCorner(context, item.url, iv, 5)
    }

}