package com.stream.wangxiang.application;

import android.util.Base64;

/**
 * 一些配置相关，包括一些key
 * Created by 张川川 on 2016/4/18.
 */
public class Config {

    // 是否调试模式
    public static final boolean DEBUG = true;

    // Bmob的appId:
    public static final String BMOB_APP_ID = "0b8ecab9676d5a1c5359c628ac58f548";

    // 首页的接口地址
    public static final String HOME_HEADLINE_URL_START = "http://c.m.163.com/nc/article/headline/T1348647909107/";
    public static final String HOME_ID = "T1348647909107";

    // 获取分类的接口
    public static final String CATEGORY_LIST_URL = "http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html";

    // 各个栏目的接口 http://c.3g.163.com/nc/article/list/ + 频道的id + /0-20.html
    private static final String CATEGORY_URL_START = "http://c.3g.163.com/nc/article/list/";

    public static final String getCategoryNewsListUrl(String categoryId, int count){
        return CATEGORY_URL_START+categoryId+"/"+count+URL_END;
    }

    // 今日要闻 http://c.3g.163.com/nc/article/list/T1429173762551/0-20.html
    private static final String TODAY_HOT_URL = "http://c.3g.163.com/nc/article/list/T1429173762551/";

    public static final String HOT_ID = "T1429173762551";

    public static String getTodayHotUrl(int count){
        return TODAY_HOT_URL+count+"-20.html";
    }


    // 本地新闻 http://c.3g.163.com/nc/article/local/ + city名字的base64编码 + /0-20.html
    private static final String LOCAL_NEWS_START = "http://c.m.163.com/nc/article/local/";

    public static final String getLocalNewsListUrl(String city, int count){
        String base64City = Base64.encodeToString(city.getBytes(), Base64.DEFAULT);
        base64City = base64City.trim();
        return LOCAL_NEWS_START + base64City+"/"+count+URL_END;
    }

    // 新闻详情的url 	http://c.m.163.com/nc/article/BKMKB6EL001168BQ/full.html
    // C.m.163.com/nc/article/+文章的postid+/full.html
    public static final String NEWS_DETAIL_URL = "http://c.m.163.com/nc/article/";

    public static final String NEWS_DETAIL_EDN = "/full.html";
    public static String URL_20_END = "/0-20.html";
    public static String URL_10_END = "/0-10.html";
    public static String URL_END = "-20.html";

    // 天气接口
    public static final String SEARCH_WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?city=";
    public static final String CITY_LIST_URL = "http://m.163.com/special/newsclient/cities.html";

    public static String getNewsDetailUrl(String postId){
        return NEWS_DETAIL_URL+postId+NEWS_DETAIL_EDN;
    }


}
