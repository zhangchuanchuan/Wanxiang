package com.stream.wangxiang.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stream.wangxiang.utils.AppUtils;

/**
 * 万象app的application
 * Created by 张川川 on 2016/4/17.
 */
public class WanxiangApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppUtils.setContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initPlatform();
    }

    private void initPlatform() {
        Fresco.initialize(this);
    }
}
