package com.stream.wangxiang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream.wangxiang.activity.HotActivity;
import com.stream.wangxiang.activity.NewsDetailActivity;
import com.stream.wangxiang.adapter.NewsItemAdapter;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.view.LoadMoreListView;
import com.stream.wangxiang.vo.NewsItem;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 张川川 on 2016/4/22.
 */
public class HotFragment extends BaseFragment {

    private LoadMoreListView mNewsList;
    private List<NewsItem> list;

    private PtrClassicFrameLayout mHotPtr;

    private NewsItemAdapter adapter;

    private final int REFRESH_CODE = 0;

    private int page = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        ImageView mBackImg = (ImageView) view.findViewById(R.id.img_back);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mNewsList = (LoadMoreListView)view.findViewById(R.id.news_list);
        mNewsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(visibleItemCount != 0) {
                    // 代表拉到最低了， 加载更多
                    if(firstVisibleItem == totalItemCount - visibleItemCount){
                        if(mNewsList.isFooterIsShow()){
                            return;
                        }
                        Log.d("zcc", "加载更多..."+page+", "+firstVisibleItem+", "+visibleItemCount+", "+totalItemCount);
                        mNewsList.setFooterShow();
                        page++;
                        GetNewsList.getHotNewsList(page*20);
                    }
                }
            }
        });


        mNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list == null){
                    return;
                }
                // 点击的是加载更多
                if(position == list.size()){
                    return;
                }

                String postId = list.get(position).getPostid();
                if(StringUtils.isNullOrEmpty(postId)){
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(NewsDetailActivity.KEY_FOR_POST_ID, postId);
                intent.setClass(getContext(), NewsDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mHotPtr = (PtrClassicFrameLayout) view.findViewById(R.id.hot_ptr);

        mHotPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                GetNewsList.getHotNewsList(REFRESH_CODE);
            }
        });
        list = new ArrayList<>();
        adapter = new NewsItemAdapter(getContext(), R.layout.item_news_digest, list);
        mNewsList.setAdapter(adapter);
        GetNewsList.getHotNewsList(REFRESH_CODE);
        setOnBusy(true);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(RefreshNewsListEvent event){
        setOnBusy(false);
        if(mHotPtr.isRefreshing()){
            list.clear();
            mHotPtr.refreshComplete();
            page = 0;
        }

        if(mNewsList.isFooterIsShow()){
            mNewsList.setFooterGone();
        }

        if(event == null){
            return;
        }

        if(event.getNewsItemList() != null){
            for(NewsItem item : event.getNewsItemList()) {
                if (!StringUtils.isNullOrEmpty(item.getLtitle()) && StringUtils.isNullOrEmpty(item.getTAGS())) {
                    list.add(item);
                }
            }
        }

        adapter.notifyDataSetChanged();


    }

}
