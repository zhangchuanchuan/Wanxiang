package com.stream.wangxiang.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

}
