package com.stream.wangxiang.net;

import com.yolanda.nohttp.OnResponseListener;
import com.yolanda.nohttp.Response;

/**
 * Created by 张川川 on 2016/4/24.
 */
public class WxOnResponseListener<T> implements OnResponseListener<T> {
    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<T> response) {

    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }

    @Override
    public void onFinish(int what) {

    }
}