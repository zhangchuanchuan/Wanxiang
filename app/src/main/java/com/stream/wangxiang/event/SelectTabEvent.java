package com.stream.wangxiang.event;

/**
 * 切换tab的event
 * Created by 张川川 on 2016/4/22.
 */
public class SelectTabEvent extends BaseEvent {

    public SelectTabEvent(){

    }

    public SelectTabEvent(int tabIndex){
        this.tabIndex = tabIndex;
    }


    private int tabIndex;

    // 来源tab，subscribe需要辨别
    private int fromTabIndex;

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public int getFromTabIndex() {
        return fromTabIndex;
    }

    public void setFromTabIndex(int fromTabIndex) {
        this.fromTabIndex = fromTabIndex;
    }
}
