package com.stream.wangxiang.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stream.wangxiang.application.Config;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.vo.NewsItem;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestQueue;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * 获取新闻列表，最后会发送RefreshNewsListEvent
 * Created by 张川川 on 2016/4/24.
 */
public class GetNewsList {

    public static void getHomeNewsList(){
        String url = Config.HOME_HEADLINE_URL;
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(RequestWhat.GET_NEWS_LIST, request, new WxOnResponseListener<JSONObject>(){

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d("zcc", response.toString());
                Log.d("zcc", response.get().toString());
                try {
                    JSONArray jsonArray = response.get().getJSONArray(Config.HOME_ID);
                    Gson gson = new Gson();
                    List<NewsItem> list = gson.fromJson(jsonArray.toString(), new TypeToken<List<NewsItem>>(){}.getType());
                    if(list != null){
                        RefreshNewsListEvent event = new RefreshNewsListEvent();
                        event.setNewsItemList(list);
                        EventBus.getDefault().post(event);
                        Log.d("zcc", list.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AppUtils.showShortToast("解析数据失败");
                }

            }

        });

    }

    public static void getCategoryNewList(final String categoryId){
        String start = Config.CATEGORY_URL_START;
        String end = Config.URL_20_END;
        String url = start+categoryId+end;

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(RequestWhat.GET_CATEGORY_NEWS_LIST, request, new WxOnResponseListener<JSONObject>(){

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d("zcc", response.toString());
                Log.d("zcc", response.get().toString());
                try {
                    JSONArray jsonArray = response.get().getJSONArray(categoryId);
                    Gson gson = new Gson();
                    List<NewsItem> list = gson.fromJson(jsonArray.toString(), new TypeToken<List<NewsItem>>(){}.getType());
                    if(list != null){
                        RefreshNewsListEvent event = new RefreshNewsListEvent();
                        event.setNewsItemList(list);
                        EventBus.getDefault().post(event);
                        Log.d("zcc", list.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AppUtils.showShortToast("解析数据失败");
                }

            }

        });
    }


}
