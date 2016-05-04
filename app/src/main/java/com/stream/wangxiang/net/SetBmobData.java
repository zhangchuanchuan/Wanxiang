package com.stream.wangxiang.net;

import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.vo.Settings;
import com.stream.wangxiang.vo.User;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 设置Bmob服务数据类
 * Created by 张川川 on 2016/5/4.
 */
public class SetBmobData {
    public static void saveUser(User user){
        user.save(AppUtils.context, new SaveListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    public static void saveSettings(Settings settings){
        settings.save(AppUtils.context, new SaveListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    public static void updateSetting(Settings settings){
        settings.update(AppUtils.context, new UpdateListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    public static void updateUser(User user){
        user.update(AppUtils.context, new UpdateListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

}
