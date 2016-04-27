package com.stream.wangxiang.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.stream.wangxiang.fragment.NewsDetailFragment;

/**
 * 新闻详情页的activity
 * Created by 张川川 on 2016/4/18.
 */
public class NewsDetailActivity extends BaseActivity{
    public static final String KEY_FOR_POST_ID = "post_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String postId = getIntent().getStringExtra(KEY_FOR_POST_ID);
        if(postId != null){
            NewsDetailFragment mFragment = NewsDetailFragment.newInstance(postId);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, mFragment);
        }else{
            finish();
        }
    }
}
