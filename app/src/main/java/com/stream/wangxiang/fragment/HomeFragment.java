package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.stream.wangxiang.adapter.NewsItemAdapter;
import com.stream.wangxiang.application.Config;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.net.GetWeatherInfo;
import com.stream.wangxiang.vo.NewsItem;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 首页的fragment
 * Created by 张川川 on 2016/4/22.
 */
public class HomeFragment extends BaseFragment {

    private ListView mNewsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mNewsList = (ListView)view.findViewById(R.id.news_list);
        GetNewsList.getHomeNewsList();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(RefreshNewsListEvent event){
        if(event == null){
            return;
        }
        List<NewsItem> list = event.getNewsItemList();
        if(list != null){
            NewsItemAdapter adapter = new NewsItemAdapter(getContext(), R.layout.item_news_digest, list);
            mNewsList.setAdapter(adapter);
        }

    }

}
