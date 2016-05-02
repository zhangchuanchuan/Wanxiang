package com.stream.wangxiang.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * 缓存工具类
 * Created by 张川川 on 2016/5/2.
 */
public class SharedPreferenceUtils {

    public static final String KEY_FOR_SUBSCRIBE_CATEGORY = "subscribe_category";

    private static SharedPreferences sharedPreferences;

    public static synchronized boolean putString(String key, String value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor.putString(key, value).commit();
    }

    public static synchronized boolean putStringSet(String key, Set<String> value){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
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



    public static String getString(String key){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, null);
    }

    public static Set<String> getStringSet(String key){
        if(sharedPreferences == null){
            sharedPreferences = AppUtils.context.getSharedPreferences("wanxiang_cache", Context.MODE_PRIVATE);
        }
        return sharedPreferences.getStringSet(key, null);
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
