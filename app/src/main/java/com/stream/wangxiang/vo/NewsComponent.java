package com.stream.wangxiang.vo;

/**
 * 新闻组件
 * Created by 张川川 on 2016/4/28.
 */
public class NewsComponent {
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMG = 1;

    private String data;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }




}
