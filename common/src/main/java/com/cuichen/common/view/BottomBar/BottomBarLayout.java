package com.cuichen.common.view.BottomBar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class BottomBarLayout extends LinearLayout implements View.OnClickListener {
    public BottomBarLayout(Context context) {
        super(context);
    }

    public BottomBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ArrayList<BottomBarView> bars = new ArrayList<>();

    public BottomBarLayout addItem(BottomBarView barView){
        bars.add(barView);
        return this;
    }

    private int mDefaultPosition = 0;
    public BottomBarLayout setDefaultPosition(int position){
        mDefaultPosition = position;
        return this;
    }


    private int defaultColor , selectColor;

    /**
     * 设置 BottomBarView 文字的颜色 ， 如果 BottomBarView已经设置颜色 ， 则以BottomBarView的为准；
     * @param defaultColor
     * @param selectColor
     * @return
     */
    public BottomBarLayout setBarTextColor(int defaultColor , int selectColor){
        this.defaultColor = defaultColor;
        this.selectColor = selectColor;
        return this;
    }

    public void initialise(){
        for (int i = 0; i < bars.size(); i++) {
            bars.get(i).setOnClickListener(this);
            bars.get(i).setBarTextColor(defaultColor , selectColor);
            this.addView(bars.get(i) , new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT , 1.0f));
        }

        setCurrtenView(mDefaultPosition);
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            if (view == getChildAt(i)){
                Log.i(TAG, "onClick: "+i);
                setCurrtenView(i);
            }
        }
    }

    private int mCurrentPosition = -1;


    /**
     * 当前选中的View样式
     * @param position
     */
    private void setCurrtenView(int position){
        if (position > this.getChildCount())return;
        if (position == mCurrentPosition){
            Log.i(TAG, "点击已经选中的");
            BottomBarView barView = (BottomBarView) getChildAt(mCurrentPosition);
            if (bottomBarListener != null) bottomBarListener.onClickSelectedBar(mCurrentPosition , barView);
        }else {
            if (mCurrentPosition != -1){ //把上一个设置透明色
                BottomBarView barView = (BottomBarView) getChildAt(mCurrentPosition);
//                barView.setBackgroundResource(R.color.color00000000);
                barView.setBarSelected(false);
                if (bottomBarListener != null) bottomBarListener.onCancelSelectedBar(mCurrentPosition , barView);
            }
            mCurrentPosition = position;
            BottomBarView barView = (BottomBarView) getChildAt(position);
//            barView.setBackgroundResource(R.color.colorPrimary);
            barView.setBarSelected(true);
            if (bottomBarListener != null) bottomBarListener.onSelectedBar(mCurrentPosition , barView);
        }

    }

    private String TAG = "BottomBarLayout";

    private BottomBarListener bottomBarListener;

    /**
     * 设置选中监听 ， 如果监听设置在 initialise 方法之后 ， 则默认选中不会回调
     * @param bottomBarListener
     */
    public void setBottomBarListener(BottomBarListener bottomBarListener){
        this.bottomBarListener = bottomBarListener;
    }
}
