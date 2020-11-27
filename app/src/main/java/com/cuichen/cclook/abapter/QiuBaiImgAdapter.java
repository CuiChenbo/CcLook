package com.cuichen.cclook.abapter;

import android.text.TextUtils;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cuichen.common.utils.GlideImageUtils;
import com.cuichen.cclook.R;
import com.cuichen.cclook.bean.QiuBaiImgBean;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;

public class QiuBaiImgAdapter extends BaseQuickAdapter<QiuBaiImgBean.ItemsBean, BaseViewHolder> {

        public QiuBaiImgAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, QiuBaiImgBean.ItemsBean item) {
            ImageView iv = helper.getView(R.id.icon);
            if (item.getUser() != null){
                helper.setText(R.id.title,item.getUser().getLogin());
                GlideImageUtils.display(getContext() , item.getUser().getThumb() , iv);
            }else {
                helper.setText(R.id.title,"未命名");
                GlideImageUtils.display(getContext() ,R.mipmap.ic_launcher , iv);
            }
            helper.setText(R.id.content,item.getContent());
           NineGridView nineGrid = helper.getView(R.id.nineGrid);
            ArrayList<ImageInfo> imageInfo;
            if (item.getAttachments() != null){ //是否有图片组
                imageInfo = new ArrayList<>();
                for (QiuBaiImgBean.ItemsBean.AttachmentsBean imageDetail : item.getAttachments()) {
                    ImageInfo info = new ImageInfo();
                    if (TextUtils.equals(imageDetail.getFormat() , "image")) { //是否是静态图
                        info.setBigImageUrl("http://" + imageDetail.getHigh_url());
                        info.setThumbnailUrl("http://" + imageDetail.getLow_url());
                    }else {
                        info.setBigImageUrl("http://" + imageDetail.getPic_url());
                        info.setThumbnailUrl("http://" + imageDetail.getPic_url());
                    }
                    imageInfo.add(info);
                }
            }else {
                imageInfo = new ArrayList<>();
                ImageInfo info = new ImageInfo();
                info.setBigImageUrl(item.getOrigin_url());
                info.setThumbnailUrl(item.getLow_url());
                imageInfo.add(info);
            }
            nineGrid.setAdapter(new NineGridViewClickAdapter(getContext(), imageInfo));
        }
}