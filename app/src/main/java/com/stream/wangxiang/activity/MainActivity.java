package com.stream.wangxiang.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.stream.wangxiang.fragment.MainInterfaceFragment;

/**
 * 主界面的Activity
 * Created by 张川川 on 2016/4/18.
 */
public class MainActivity extends BaseActivity {

    private long pressTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            MainInterfaceFragment mainInterfaceFragment = new MainInterfaceFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainInterfaceFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - pressTime < 2000){
            finish();
        }else{
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            pressTime = currentTime;
        }
    }
}
