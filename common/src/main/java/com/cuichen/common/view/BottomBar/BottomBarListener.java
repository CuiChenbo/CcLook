package com.cuichen.common.view.BottomBar;

public interface BottomBarListener {
    //选中的Bar
    void onSelectedBar(int position , BottomBarView barView);
    //取消选中的Bar
    void onCancelSelectedBar(int position , BottomBarView barView);
    //点击已选中的Bar
    void onClickSelectedBar(int position , BottomBarView barView);
}
