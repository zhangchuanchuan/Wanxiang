package com.stream.wangxiang.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.stream.wangxiang.fragment.MainInterfaceFragment;

/**
 * 主界面的Activity
 * Created by 张川川 on 2016/4/18.
 */
public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            MainInterfaceFragment mainInterfaceFragment = new MainInterfaceFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainInterfaceFragment).commit();
        }
    }
}
