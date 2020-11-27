package com.cuichen.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.lzy.ninegrid.NineGridView;

/**
 * Glide 加载
 */
public class NineImageLoader implements NineGridView.ImageLoader {

    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        GlideImageUtils.displaynoCrop(context, url, imageView);
    }

    @Override
    public void onDisplayImage(Context context, ImageView imageView, Integer src) {
//        GlideImageUtils.displaynoCrop(context, src, imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}