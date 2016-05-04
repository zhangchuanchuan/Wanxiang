package com.stream.wangxiang.event;

/**
 * Event的基类
 * Created by 张川川 on 2016/4/18.
 */
public class BaseEvent{
    private boolean isSucceed;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }
}
