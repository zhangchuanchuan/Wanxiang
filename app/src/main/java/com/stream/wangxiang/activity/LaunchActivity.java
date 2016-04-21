package com.stream.wangxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 启动的Activity
 * Created by 张川川 on 2016/4/17.
 */
public class LaunchActivity extends BaseActivity {

    // 延迟加载的消息类型
    private final int MSG_TYPE_DELAY_START = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Message startMsg = new Message();
        startMsg.what = MSG_TYPE_DELAY_START;
        startHandler.sendMessageDelayed(startMsg, 2000);
    }

    @Override
    public void onBackPressed() {
    }

    // 这是启动延迟使用的handler
    private Handler startHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_TYPE_DELAY_START:
                    jumpToMainActivity();
                    break;
            }
        }
    };

    private void jumpToMainActivity(){
        Intent mainIntent = new Intent();
        mainIntent.setClass(this, MainActivity.class);
        this.startActivity(mainIntent);
        finish();
    }
}
