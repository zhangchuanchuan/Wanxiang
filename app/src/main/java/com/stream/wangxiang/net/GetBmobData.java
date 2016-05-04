package com.stream.wangxiang.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stream.wangxiang.event.GetSettingsListEvent;
import com.stream.wangxiang.event.GetUserListEvent;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.vo.Settings;
import com.stream.wangxiang.vo.User;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import de.greenrobot.event.EventBus;

/**
 * 获取Bmob服务相关的数据
 * Created by 张川川 on 2016/5/4.
 */
public class GetBmobData {


    public static void getUserList(){
        BmobQuery query = new BmobQuery("User");

        query.findObjects(AppUtils.context, new FindCallback() {

            @Override
            public void onSuccess(JSONArray arg0) {
                Log.d("zcc", arg0.toString());
                Gson gson = new Gson();
                List<User> userList = gson.fromJson(arg0.toString(), new TypeToken<List<User>>(){}.getType());
                GetUserListEvent event = new GetUserListEvent();
                event.setUserList(userList);
                event.setSucceed(true);
                EventBus.getDefault().post(event);

            }

            @Override
            public void onFailure(int arg0, String arg1) {
                Log.d("zcc", arg1.toString());
                GetUserListEvent event = new GetUserListEvent();
                event.setSucceed(false);
                EventBus.getDefault().post(event);
                AppUtils.showShortToast(arg1);
            }
        });
    }

    public static void getSettingList(){
        BmobQuery query = new BmobQuery("Settings");

        query.findObjects(AppUtils.context, new FindCallback() {

            @Override
            public void onSuccess(JSONArray arg0) {
                Log.d("zcc", arg0.toString());
                Gson gson = new Gson();
                List<Settings> settingsList = gson.fromJson(arg0.toString(), new TypeToken<List<Settings>>(){}.getType());
                GetSettingsListEvent event = new GetSettingsListEvent();
                event.setSettingsList(settingsList);
                event.setSucceed(true);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                Log.d("zcc", arg1.toString());
                GetSettingsListEvent event = new GetSettingsListEvent();
                event.setSucceed(false);
                EventBus.getDefault().post(event);

            }
        });
    }

}
