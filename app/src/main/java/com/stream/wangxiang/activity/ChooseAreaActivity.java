package com.stream.wangxiang.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stream.wangxiang.fragment.ChooseAreaFragment;

/**
 * 选择地区activity
 * Created by 张川川 on 2016/5/3.
 */
public class ChooseAreaActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new ChooseAreaFragment()).commit();
    }
}
