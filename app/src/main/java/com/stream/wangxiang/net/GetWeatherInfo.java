package com.stream.wangxiang.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stream.wangxiang.application.Config;
import com.stream.wangxiang.event.GetCityListEvent;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.vo.Cities;
import com.stream.wangxiang.vo.City;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


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
                try {
                    JSONArray allCities = response.get().getJSONArray("cities");

                    Gson gson = new Gson();
                    List<Cities> categoryList = new ArrayList<>();

                    List<String> abcList = StringUtils.getABCStringArray();
                    for(int i=0; i<abcList.size(); i++) {
                        Log.d("zcc", abcList.get(i));

                        String s = abcList.get(i);

                        JSONObject cityListObject = allCities.getJSONObject(i);
                        Log.d("zcc", cityListObject.toString());
                        JSONArray cityListArray = cityListObject.getJSONArray(s);
                        List<City> cityList = gson.fromJson(cityListArray.toString(), new TypeToken<List<City>>(){}.getType());
                        Cities cities = new Cities();
                        cities.setCityList(cityList);
                        cities.setCategory(s);
                        categoryList.add(cities);
                    }
                    Log.d("zcc", categoryList.toArray().toString());
                    GetCityListEvent event = new GetCityListEvent();
                    event.setCategoryCityList(categoryList);
                    EventBus.getDefault().post(event);

                } catch (JSONException e) {
                    e.printStackTrace();
                    AppUtils.showShortToast("解析数据失败");
                }
            }
        });

    }
}
