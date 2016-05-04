package com.stream.wangxiang.vo;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 设置类
 * Created by 张川川 on 2016/5/4.
 */
public class Settings extends BmobObject {
    private String username;

    private Boolean has_settings;

    private List<Integer> subscribe_category;

    private String local_city;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean isHas_settings() {
        return has_settings;
    }

    public void setHas_settings(Boolean has_settings) {
        this.has_settings = has_settings;
    }

    public List<Integer> getSubscribe_category() {
        return subscribe_category;
    }

    public void setSubscribe_category(List<Integer> subscribe_category) {
        this.subscribe_category = subscribe_category;
    }

    public String getLocal_city() {
        return local_city;
    }

    public void setLocal_city(String local_city) {
        this.local_city = local_city;
    }
}
