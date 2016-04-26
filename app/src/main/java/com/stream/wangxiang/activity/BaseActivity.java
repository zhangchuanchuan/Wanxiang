package com.stream.wangxiang.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import de.greenrobot.event.EventBus;

/**
 * 基本的Activity
 * Created by 张川川 on 2016/4/17.
 */
public class BaseActivity extends FragmentActivity {

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     *  loadding动效 默认可以取消
     * @param isBusy true 显示， false 消失
     */
    public void setOnBusy(boolean isBusy){
        this.setOnBusy(isBusy, true);
    }

    /**
     *
     * @param isBusy true 显示， false 消失
     * @param canCanceled 是否可以取消
     */
    public void setOnBusy(boolean isBusy, boolean canCanceled){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(canCanceled);
            progressDialog.setMessage("加载中...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        if(isBusy){
            progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }
}
