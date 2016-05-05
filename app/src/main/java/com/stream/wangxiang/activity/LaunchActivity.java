package com.stream.wangxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.stream.wangxiang.event.GetSettingsListEvent;
import com.stream.wangxiang.event.GetUserListEvent;
import com.stream.wangxiang.event.LoginSuccessEvent;
import com.stream.wangxiang.net.GetBmobData;
import com.stream.wangxiang.net.SetBmobData;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.LoginUtils;
import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.vo.Settings;
import com.stream.wangxiang.vo.User;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 启动的Activity
 * Created by 张川川 on 2016/4/17.
 */
public class LaunchActivity extends BaseActivity {

    // 延迟加载的消息类型
    private final int MSG_TYPE_DELAY_START = 0;

    private long start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start = System.currentTimeMillis();
        EventBus.getDefault().register(this);
        if(SettingUtils.hasLocalUser()) {
            GetBmobData.getUserList();
        }else {
            Message startMsg = new Message();
            startMsg.what = MSG_TYPE_DELAY_START;
            startHandler.sendMessageDelayed(startMsg, 2000);
        }
    }

    @Override
    public void onBackPressed() {
    }

    // 这是启动延迟使用的handler
    private Handler startHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_TYPE_DELAY_START:
                    jumpToMainActivity();
                    break;
            }
        }
    };

    private void jumpToMainActivity(){
        Intent mainIntent = new Intent();
        mainIntent.setClass(this, MainActivity.class);
        super.startActivity(mainIntent, false);
        super.finish(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(GetUserListEvent event){

        if(event.isSucceed()) {
            List<User> list = event.getUserList();
            for (User user : list) {
                if (user.getUsername().equals(SettingUtils.getLocalUser()) && user.getPassword().equals(SettingUtils.getLocalPassword())) {
                    LoginUtils.isLogin = true;
                    LoginUtils.bmob_user = user;

                    GetBmobData.getSettingList();
                    break;
                }
            }

            if (!LoginUtils.isLogin) {
                sendMsg();
                AppUtils.showShortToast("自动登陆失败，用户名或密码错误");
            }
        }else{
            sendMsg();
            AppUtils.showShortToast("自动登录失败");
        }

    }

    public void onEventMainThread(GetSettingsListEvent event){
        List<Settings> settingsList =  event.getSettingsList();
        for(Settings setting : settingsList){
            // 存在设置
            if(setting.getUsername().equals(LoginUtils.bmob_user.getUsername())){

                LoginUtils.bmob_settings = setting;
                break;
            }
        }

        if(LoginUtils.bmob_settings == null){
            // 保存设置到bmob
            Settings settings = new Settings();
            settings.setUsername(LoginUtils.bmob_user.getUsername());
            settings.setLocal_city(SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_LOCAL_CITY, SettingUtils.defaultCity));
            settings.setSubscribe_category(SettingUtils.getSubscribeCategoryList());
            SetBmobData.saveSettings(settings);
        }

        // 登录成功
        AppUtils.showShortToast("自动登录成功");
        sendMsg();

    }

    private void sendMsg(){
        Message startMsg = new Message();
        startMsg.what = MSG_TYPE_DELAY_START;
        long delay = System.currentTimeMillis() - start;
        if(delay > 2000) {
            startHandler.sendMessage(startMsg);
        }else{
            startHandler.sendMessageDelayed(startMsg, delay);
        }
    }

}
