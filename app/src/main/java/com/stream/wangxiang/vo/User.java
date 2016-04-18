package com.stream.wangxiang.vo;

import cn.bmob.v3.BmobObject;

/**
 * 用户的vo
 * Created by 张川川 on 2016/4/18.
 */
public class User extends BmobObject{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
