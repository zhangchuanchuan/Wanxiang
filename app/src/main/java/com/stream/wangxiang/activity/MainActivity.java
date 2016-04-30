package com.stream.wangxiang.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.fragment.BaseFragment;
import com.stream.wangxiang.fragment.HomeFragment;
import com.stream.wangxiang.fragment.MainFragment;
import com.stream.wangxiang.fragment.MainInterfaceFragment;

import de.greenrobot.event.EventBus;

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
            MainFragment mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainInterfaceFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - pressTime < 2000){
            super.finish(false);
        }else{
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            pressTime = currentTime;
        }
    }



}
