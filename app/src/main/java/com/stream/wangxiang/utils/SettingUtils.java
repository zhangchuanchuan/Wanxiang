package com.stream.wangxiang.utils;

import com.stream.wangxiang.vo.Settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SocketHandler;

/**
 * 设置工具类
 * Created by 张川川 on 2016/5/3.
 */
public class SettingUtils {
    public static boolean isSettingNew = false;

    public static boolean isCityNew = false;

    public static Set<String> getDefaultSubscribeStringSet(){
        Set<String> set = new TreeSet<>();
        set.add("3");
        set.add("5");
        set.add("6");
        set.add("7");
        set.add("11");
        set.add("15");
        set.add("16");
        set.add("17");
        set.add("18");
        set.add("24");
        set.add("26");
        return set;
    }

    public static String defaultCity = "北京";

    public static void loadBmobSetting(Settings setting){
        String local_city = setting.getLocal_city();

    }

    // 获取本地的订阅设置
    public static List<Integer> getSubscribeCategoryList(){
        List<Integer> list = new ArrayList<>();
        Set<String> set = SharedPreferenceUtils.getStringSet(SharedPreferenceUtils.KEY_FOR_SUBSCRIBE_CATEGORY, getDefaultSubscribeStringSet());
        for(String s : set){
            list.add(Integer.valueOf(s));
        }

        return list;
    }

    public static List<Integer> getSubscribeCategoryList(Set<String> set){
        List<Integer> list = new ArrayList<>();

        for(String s : set){
            list.add(Integer.valueOf(s));
        }

        return list;
    }

    public static void saveUserToLocal(String username, String password){
        SharedPreferenceUtils.putString(SharedPreferenceUtils.KEY_FOR_USER_NAME, username);
        SharedPreferenceUtils.putString(SharedPreferenceUtils.KEY_FOR_PASS_WORD, password);
    }

    public static boolean hasLocalUser(){
        if(StringUtils.isNullOrEmpty(getLocalUser()) || StringUtils.isNullOrEmpty(getLocalPassword())){
            return false;
        }
        return true;
    }

    public static String getLocalUser(){
        return SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_USER_NAME, "");
    }

    public static String getLocalPassword(){
        return SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_PASS_WORD, "");
    }

    public static void setLoginOut(){
        LoginUtils.isLogin =false;
        LoginUtils.bmob_settings = null;
        LoginUtils.bmob_user = null;
        SharedPreferenceUtils.putString(SharedPreferenceUtils.KEY_FOR_PASS_WORD, "");

    }



}
