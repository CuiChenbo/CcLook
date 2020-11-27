package com.cuichen.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cuichen.common.R;
import com.cuichen.common.view.UiUtils;

/**
 * NAME:CCB on 2016/10/13 16:27
 * Glide图片加载框架  circle
 */
public class GlideImageUtils {

    private static RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.loading)
            .error(R.drawable.zanwutupian)
            .centerCrop();
    /**
     * 加载普通图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void display(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载普通图片
     * @param context
     * @param src
     * @param imageView
     */
    public static void display(Context context, Integer src, ImageView imageView) {
        Glide.with(context)
                .load(src)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载普通图片 , 有过渡动画
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayTransition(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载图片，并切成圆形
     *
     * @param context
     * @param src
     * @param imageView
     */
    public static void DisplayCircle(final Context context, Integer src, final ImageView imageView) {
        DisplayCircle(context,src , null, imageView);
    }
    public static void DisplayCircle(final Context context, String url, final ImageView imageView) {
        DisplayCircle(context,0 , url, imageView);
    }
    public static void DisplayCircle(final Context context,Integer src, String url, final ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(url == null ? src : url)
                .apply(options)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void DisplayRoundCorner(final Context context, String url, final ImageView imageView,
                                          final int dpCorner) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(options)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        circularBitmapDrawable.setCornerRadius(UiUtils.dip2px(context , dpCorner));
                        circularBitmapDrawable.setAntiAlias(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }


    /**
     * 加载普通图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void displaynoCrop(Context context, String url, ImageView imageView) {
       RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.zanwutupian);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

}
