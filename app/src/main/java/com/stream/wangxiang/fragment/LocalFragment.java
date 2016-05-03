package com.stream.wangxiang.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.stream.wangxiang.activity.NewsDetailActivity;
import com.stream.wangxiang.adapter.NewsItemAdapter;
import com.stream.wangxiang.event.GetWeatherInfoEvent;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.net.GetWeatherInfo;
import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.utils.ViewUtils;
import com.stream.wangxiang.view.LoadMoreListView;
import com.stream.wangxiang.vo.NewsItem;
import com.stream.wangxiang.vo.WeatherVo;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 本地新闻的fragment
 * Created by 张川川 on 2016/4/22.
 */
public class LocalFragment extends BaseFragment {


    private View weatherView;

    // listView
    private LoadMoreListView mMainLayout;

    // 下拉刷新控件
    private PtrClassicFrameLayout mLocalFramelayout;

    // 新闻数据和adapter
    private List<NewsItem> mNewsList;
    private NewsItemAdapter newsItemAdapter;

    private final int REFRESH_CODE = 0;

    // 当前的新闻页数
    private int page = 0;

    // listView的header数量，onclick需要它，position减去它
    private int headerNum = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);
        mMainLayout = (LoadMoreListView) view.findViewById(R.id.local_main);
        mLocalFramelayout = (PtrClassicFrameLayout) view.findViewById(R.id.local_ptr);

        // 设置刷新的handler
        mLocalFramelayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getLocalNewsData();
            }
        });

        // 设置list的滑动事件和item点击事件
        setListViewOnScroller(mMainLayout);
        setOnItemClicked(mMainLayout);

        // 初始化list
        mNewsList = new ArrayList<>();
        newsItemAdapter = new NewsItemAdapter(getContext(), R.layout.item_news_digest, mNewsList);
        mMainLayout.setAdapter(newsItemAdapter);

        setOnBusy(true);
        getLocalNewsData();
        return view;
    }

    // 滑动事件
    private void setListViewOnScroller(final LoadMoreListView mMainLayout) {
        mMainLayout.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(visibleItemCount != 0) {

                    if(weatherView != null && totalItemCount == 1){
                        return;
                    }

                    // 代表拉到最低了， 加载更多
                    if(firstVisibleItem == totalItemCount - visibleItemCount){

                        if(mMainLayout.isFooterIsShow()){
                            return;
                        }
                        Log.d("zcc", "加载更多..."+page+", "+firstVisibleItem+", "+visibleItemCount+", "+totalItemCount);
                        mMainLayout.setFooterShow();
                        page++;
                        loadMoreNews(page*20);
                    }
                }
            }
        });
    }

    // item 点击事件
    private void setOnItemClicked(LoadMoreListView mMainLayout){
        mMainLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - headerNum;
                if(mNewsList == null){
                    return;
                }
                // 点击的是加载更多
                if(position == mNewsList.size()){
                    return;
                }

                String postId = mNewsList.get(position).getDocid();
                if(StringUtils.isNullOrEmpty(postId)){
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(NewsDetailActivity.KEY_FOR_POST_ID, postId);
                intent.setClass(getContext(), NewsDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    // 刷新数据
    private void getLocalNewsData() {
        String city = SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_LOCAL_CITY, SettingUtils.defaultCity);
        GetNewsList.getLocalNewsList(city, REFRESH_CODE);
        GetWeatherInfo.getWeather(city);
    }

    private void loadMoreNews(int count){
        String city = SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_LOCAL_CITY, SettingUtils.defaultCity);
        GetNewsList.getLocalNewsList(city, count);
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

        if(mLocalFramelayout.isRefreshing()){
            mNewsList.clear();
            mLocalFramelayout.refreshComplete();
            page = 0;
        }

        if(mMainLayout.isFooterIsShow()){
            mMainLayout.setFooterGone();
        }

        if(event == null){
            return;
        }

        if(event.getNewsItemList() != null){
            for(NewsItem item : event.getNewsItemList()) {
                if (!StringUtils.isNullOrEmpty(item.getTitle()) && StringUtils.isNullOrEmpty(item.getTAGS()) && !StringUtils.isNullOrEmpty(item.getDigest())) {
                    mNewsList.add(item);
                }
            }
        }
        newsItemAdapter.notifyDataSetChanged();

    }

    public void onEventMainThread(GetWeatherInfoEvent event){
        if(event != null){
            WeatherVo vo = event.getWeatherVo();
            if(weatherView == null) {
                weatherView = ViewUtils.getWeatherView(vo, getContext());
                mMainLayout.addHeaderView(weatherView);
            }else{
                mMainLayout.removeHeaderView(weatherView);
                weatherView = ViewUtils.getWeatherView(vo, getContext());
                mMainLayout.addHeaderView(weatherView);
            }
            headerNum = 1;
        }
    }


}
