package com.stream.wangxiang.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.stream.wangxiang.net.SetBmobData;
import com.stream.wangxiang.vo.Settings;

import java.util.Set;

/**
 * 缓存工具类
 * Created by 张川川 on 2016/5/2.
 */
public class SharedPreferenceUtils {

    public static final String KEY_FOR_SUBSCRIBE_CATEGORY = "subscribe_category";

    public static final String KEY_FOR_LOCAL_CITY = "local_ctiy";

    public static final String KEY_FOR_USER_NAME = "user_name";

    public static final String KEY_FOR_PASS_WORD = "password";

    private static SharedPreferences sharedPreferences;

    public static synchronized boolean putString(String key, String value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(LoginUtils.isLogin && key.equals(KEY_FOR_LOCAL_CITY)){
            if(LoginUtils.bmob_settings != null){
                LoginUtils.bmob_settings.setLocal_city(value);
                SetBmobData.updateSetting(LoginUtils.bmob_settings);
            }
        }

        return editor.putString(key, value).commit();
    }

    public static synchronized boolean putStringSet(String key, Set<String> value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        if(LoginUtils.isLogin && key.equals(KEY_FOR_SUBSCRIBE_CATEGORY)){
            if(LoginUtils.bmob_settings != null){
                LoginUtils.bmob_settings.setSubscribe_category(SettingUtils.getSubscribeCategoryList(value));
                SetBmobData.updateSetting(LoginUtils.bmob_settings);
            }
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor.putStringSet(key, value).commit();
    }

    public static synchronized boolean putInt(String key, int value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor.putInt(key, value).commit();
    }

    public static synchronized boolean putFloat(String key, float value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor.putFloat(key, value).commit();
    }

    public static synchronized boolean putBoolean(String key, boolean value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor.putBoolean(key, value).commit();
    }

    public static synchronized boolean putLong(String key, long value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor.putLong(key, value).commit();
    }



    public static String getString(String key, String def){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, def);
    }

    public static Set<String> getStringSet(String key, Set<String> def){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getStringSet(key, def);
    }

    public static int getInt(String key){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, 0);
    }

    public static boolean getBoolean(String key){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, false);
    }

}
