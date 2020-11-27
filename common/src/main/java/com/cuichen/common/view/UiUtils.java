package com.cuichen.common.view;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class UiUtils {

    private static Toast t;

    public static void showToast(Context c , String s){
        if (t == null)t = Toast.makeText(c,s, Toast.LENGTH_SHORT);
        t.setText(s);
        t.show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static int dp2px(float dpValue) {
            return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        public static float px2dp(float pxValue) {
            return (pxValue / Resources.getSystem().getDisplayMetrics().density);
        }
}
