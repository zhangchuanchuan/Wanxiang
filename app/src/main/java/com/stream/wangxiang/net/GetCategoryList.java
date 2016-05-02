package com.stream.wangxiang.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stream.wangxiang.application.Config;
import com.stream.wangxiang.event.GetCategoryListEvent;
import com.stream.wangxiang.vo.CategoryVo;
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
 * 获取分类列表
 * Created by 张川川 on 2016/5/2.
 */
public class GetCategoryList {

    /**
     *  获取分类列表
     */
    public static void getCagegoryList(){
        String url = Config.CATEGORY_LIST_URL;

        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(url);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(RequestWhat.GET_CATEGROY_LIST, request, new WxOnResponseListener<JSONObject>(){

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d("zcc", response.toString());
                Log.d("zcc", response.get().toString());

                JSONArray jsonArray = null;

                try {
                    jsonArray = response.get().getJSONArray("tList");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(jsonArray == null){
                    return;
                }

                Gson gson = new Gson();
                List<CategoryVo> list = gson.fromJson(jsonArray.toString(), new TypeToken<List<CategoryVo>>(){}.getType());

                // 发送广播
                GetCategoryListEvent event = new GetCategoryListEvent();
                event.setCategoryVoList(list);
                EventBus.getDefault().post(event);

            }

        });

    }
}
