package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.User;

import java.util.List;

/**
 *
 * Created by 张川川 on 2016/5/4.
 */
public class GetUserListEvent extends  BaseEvent{
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
