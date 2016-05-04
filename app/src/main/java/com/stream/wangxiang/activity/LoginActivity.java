package com.stream.wangxiang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stream.wangxiang.fragment.LoginFragment;

/**
 * 登录注册Activity
 * Created by 张川川 on 2016/5/4.
 */
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new LoginFragment()).commit();
    }
}
