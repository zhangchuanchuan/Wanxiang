package com.stream.wangxiang.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stream.wangxiang.utils.AppUtils;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import cn.bmob.v3.Bmob;

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
        Context context = getApplicationContext();
        NoHttp.init(this);
        Fresco.initialize(context);
        Bmob.initialize(context, Config.BMOB_APP_ID);
        if(Config.DEBUG){
            Logger.setDebug(true);
            Logger.setTag("zcc");
        }
    }
}
