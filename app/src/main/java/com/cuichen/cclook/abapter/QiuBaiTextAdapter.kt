package com.cuichen.cclook.abapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.cuichen.common.utils.GlideImageUtils
import com.cuichen.cclook.R
import com.cuichen.cclook.bean.QiuBaiBean

class QiuBaiTextAdapter : BaseQuickAdapter<QiuBaiBean.ItemsBean, BaseViewHolder> {
    constructor(layoutResId: Int, data: MutableList<QiuBaiBean.ItemsBean>?) : super(layoutResId, data)
    constructor(layoutResId: Int) : super(layoutResId) {}

    override fun convert(helper: BaseViewHolder, item: QiuBaiBean.ItemsBean) {
        val iv = helper.getView<ImageView>(R.id.iv)
        if (item.user != null) {
            helper.setText(R.id.tvt, item.user.login)
            GlideImageUtils.DisplayCircle(context, item.user.thumb, iv)
        } else {
            helper.setText(R.id.tvt, "糗友")
            GlideImageUtils.display(context, R.drawable.icon_qq, iv)
        }
        val tv = helper.getView<TextView>(R.id.tv)
        if (item.isUnfold) {
            tv.maxLines = 199
        } else {
            tv.maxLines = 15
        }
        tv.setOnLongClickListener {
            item.isUnfold = true
            setData(helper.adapterPosition, item)
            true
        }
        tv.text = item.content
    }
}