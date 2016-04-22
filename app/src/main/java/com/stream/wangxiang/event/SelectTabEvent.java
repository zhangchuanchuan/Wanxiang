package com.stream.wangxiang.event;

/**
 * 切换tab的event
 * Created by 张川川 on 2016/4/22.
 */
public class SelectTabEvent extends BaseEvent {
    private int tabIndex;

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
}
