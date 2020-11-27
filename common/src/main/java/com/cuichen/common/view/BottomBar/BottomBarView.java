package com.cuichen.common.view.BottomBar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cuichen.common.R;
import com.cuichen.common.view.UiUtils;

public class BottomBarView extends LinearLayout {

    private ImageView iv;
    private TextView tv;

    public BottomBarView(Context context) {
        super(context);
        init(context , null , null, "默认" );
    }

    public BottomBarView(Context context , String text) {
        super(context);
        init(context , null , null, text);
    }

    /**
     *
     * @param context 上下文
     * @param defaultDrawable 默认图片
     * @param selectDrawable 选中图片
     * @param text 导航文字
     */
    public BottomBarView(Context context , Drawable defaultDrawable , Drawable selectDrawable , String text) {
        super(context);
        init(context, defaultDrawable, selectDrawable, text, defaultColor, selectColor);
    }

    /**
     *
     * @param context 上下文
     * @param defaultDrawable 默认图片
     * @param selectDrawable 选中图片
     * @param text 导航文字
     * @param defaultColor 文字默认颜色
     * @param selectColor 文字选中颜色
     */
    public BottomBarView(Context context , Drawable defaultDrawable , Drawable selectDrawable , String text , int defaultColor , int selectColor) {
        super(context);
        init(context, defaultDrawable, selectDrawable, text, defaultColor, selectColor);
    }

    public BottomBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Drawable defaultDrawable , selectDrawable;
    private int defaultColor , selectColor;
    private int color111 = R.color.color111;
    private int color666 = R.color.color666;

    private void init(Context context , Drawable defaultDrawable , Drawable selectDrawable , String text){
        init(context, defaultDrawable, selectDrawable, text ,color666 , color111 );
    }

    private void init(Context context , Drawable defaultDrawable , Drawable selectDrawable , String text , int defaultColor , int selectColor){
        this.defaultDrawable = defaultDrawable;
        this.selectDrawable = selectDrawable;
        this.defaultColor = defaultColor;
        this.selectColor = selectColor;

        this.setOrientation(VERTICAL);
        this.setGravity(Gravity.CENTER);

        if (defaultDrawable != null) {
            iv = new ImageView(context);
            iv.setImageDrawable(defaultDrawable);
            ViewGroup.LayoutParams ivP = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            ivP.width = UiUtils.dip2px(getContext(), 30);
            ivP.height = UiUtils.dip2px(getContext(), 30);
            iv.setLayoutParams(ivP);
            addView(iv);
        }

        tv = new TextView(context);
        tv.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT));
        tv.setText(text);
        tv.setTextColor(getColor(defaultColor));
        addView(tv);

    }

    public void setBarTextColor(int defaultColor , int selectColor){
        if (this.defaultColor == 0) {
            this.defaultColor = defaultColor;
            tv.setTextColor(getColor(defaultColor));
        }

        if (this.selectColor == 0)
        this.selectColor = selectColor;
    }


    public void setBarSelected(boolean isSelected){
        if (iv != null){
            iv.setImageDrawable(isSelected ? selectDrawable : defaultDrawable);
        }
        if (tv != null){
            tv.setTextColor(isSelected? getColor(selectColor):getColor(defaultColor));
        }
    }

    public TextView getTextView(){
        return tv;
    }

    private int getColor(int c){
        if (c == 0){
            return getResources().getColor(R.color.color666);
        }
        return getResources().getColor(c);
    }
}
