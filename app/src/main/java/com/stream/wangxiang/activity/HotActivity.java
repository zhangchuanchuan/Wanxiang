package com.stream.wangxiang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.stream.wangxiang.fragment.HotFragment;

/**
 * 今日要闻的Activity
 * Created by 张川川 on 2016/4/21.
 */
public class HotActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HotFragment mFragment = new HotFragment();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, mFragment).commit();
    }
}
