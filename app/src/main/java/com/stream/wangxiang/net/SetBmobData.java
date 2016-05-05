package com.stream.wangxiang.net;

import android.util.Log;

import com.stream.wangxiang.event.RegisterEvent;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.LoginUtils;
import com.stream.wangxiang.vo.Settings;
import com.stream.wangxiang.vo.User;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import de.greenrobot.event.EventBus;

/**
 * 设置Bmob服务数据类
 * Created by 张川川 on 2016/5/4.
 */
public class SetBmobData {
    public static void saveUser(User user){
        user.save(AppUtils.context, new SaveListener() {
            @Override
            public void onSuccess() {
                RegisterEvent event = new RegisterEvent();
                event.setSucceed(true);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onFailure(int i, String s) {
                RegisterEvent event = new RegisterEvent();
                event.setSucceed(false);
                event.setMsg(s);
                EventBus.getDefault().post(event);
            }
        });
    }

    public static void saveSettings(final Settings settings){
        settings.save(AppUtils.context, new SaveListener() {
            @Override
            public void onSuccess() {
                AppUtils.showShortToast("保存设置到云端成功");
                LoginUtils.bmob_settings = settings;
            }

            @Override
            public void onFailure(int i, String s) {
                AppUtils.showShortToast("保存设置失败");
            }
        });
    }

    public static void updateSetting(final Settings settings){
        settings.update(AppUtils.context, new UpdateListener() {
            @Override
            public void onSuccess() {
                AppUtils.showShortToast("更新设置到云端成功");
                LoginUtils.bmob_settings = settings;
            }

            @Override
            public void onFailure(int i, String s) {
                AppUtils.showShortToast("更新设置到云端失败");
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
