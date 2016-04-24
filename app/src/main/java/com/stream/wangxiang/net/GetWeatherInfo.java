package com.stream.wangxiang.net;

import com.stream.wangxiang.application.Config;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

import org.json.JSONObject;

/**
 * 获取天气信息的类
 * Created by 张川川 on 2016/4/24.
 */
public class GetWeatherInfo {

    public static void getCityList(){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(Config.CITY_LIST_URL);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(RequestWhat.GET_CITY_LIST, request, new WxOnResponseListener<JSONObject>(){
            @Override
            public void onSucceed(int what, Response<JSONObject> response) {

            }
        });

    }
}
