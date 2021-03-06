package com.stream.wangxiang.net;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stream.wangxiang.application.Config;
import com.stream.wangxiang.event.GetNewsDetailEvent;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.vo.NewsDetailVo;
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

    /**
     * 获取新闻列表， 最新为0,加载更多为其他
     * @param count 0，20 ，40...
     */
    public static void getHomeNewsList(int count){
        String url = Config.HOME_HEADLINE_URL_START + count+Config.URL_END;
        getNewsList(RequestWhat.GET_NEWS_LIST, url, Config.HOME_ID);
    }

    public static void getHotNewsList(int count){
        String url = Config.getTodayHotUrl(count);
        getNewsList(RequestWhat.GET_HOT_NEWS_LIST, url, Config.HOT_ID);
    }

    public static void getNewsList(int what, String url, final String postId){
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(what, request, new WxOnResponseListener<JSONObject>(){

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d("zcc", response.toString());
                Log.d("zcc", response.get().toString());
                try {
                    JSONArray jsonArray = response.get().getJSONArray(postId);
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
                    if(what == RequestWhat.GET_LOCAL_NEWS_LIST){
                        AppUtils.showShortToast("抱歉...所选城市没有新闻数据");
                    }
                    AppUtils.showShortToast("解析数据失败");
                    EventBus.getDefault().post(new RefreshNewsListEvent());
                }

            }

            @Override
            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                super.onFailed(what, url, tag, exception, responseCode, networkMillis);
                RefreshNewsListEvent event = new RefreshNewsListEvent();
                EventBus.getDefault().post(event);
            }
        });
    }



    public static void getCategoryNewsList(String categoryId, int count){
        String url = Config.getCategoryNewsListUrl(categoryId, count);
        getNewsList(RequestWhat.GET_CATEGORY_NEWS_LIST, url, categoryId);
    }

    public static void getLocalNewsList(String city, int count){
        String url = Config.getLocalNewsListUrl(city, count);
        getNewsList(RequestWhat.GET_LOCAL_NEWS_LIST, url, city);
    }

    public static void getNewsDetail(final String postId){
        String url = Config.getNewsDetailUrl(postId);

        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(RequestWhat.GET_NEWS_DETAIL, request, new WxOnResponseListener<JSONObject>(){

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d("zcc", response.toString());
                Log.d("zcc", response.get().toString());

                JSONObject jsonObject  = null;
                try {
                    jsonObject = response.get().getJSONObject(postId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                NewsDetailVo detailVo = gson.fromJson(jsonObject.toString(), NewsDetailVo.class);
                GetNewsDetailEvent event = new GetNewsDetailEvent();
                event.setDetailVo(detailVo);
                EventBus.getDefault().post(event);

            }

        });

    }


}
