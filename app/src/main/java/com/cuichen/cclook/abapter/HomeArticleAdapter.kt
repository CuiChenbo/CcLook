package com.cuichen.cclook.abapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.cuichen.cclook.R
import com.cuichen.cclook.bean.HomeArticleBean.DataBean.DatasBean

class HomeArticleAdapter(layoutResId: Int) : BaseQuickAdapter<DatasBean, BaseViewHolder>(layoutResId) {
    override fun convert(vh: BaseViewHolder, datas: DatasBean) {
        vh.setText(R.id.tvTitle, datas.title)
            .setText(R.id.tvAuthor, datas.shareUser)
            .setText(R.id.tvTime, datas.niceDate)
            .setText(R.id.tvConent, datas.desc)
            .setText(R.id.tvOrigin, datas.superChapterName)
        vh.setGone(R.id.tvConent, TextUtils.isEmpty(datas.desc))
    }
}