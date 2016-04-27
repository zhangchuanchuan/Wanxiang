package com.stream.wangxiang.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import com.stream.wanxiang.R;

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

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        this.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    /**
     *  开启activity时，是否有动画
     * @param intent 传入的intent
     * @param hasAnim 是否有动画，true 有， false 没有
     */
    public void startActivity(Intent intent, boolean hasAnim){
        if(hasAnim){
            this.startActivity(intent);
        }else{
            super.startActivity(intent);
        }
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    public void finish(boolean hasAnim){
        if(hasAnim){
            this.finish();
        }else{
            super.finish();
        }
    }
}
