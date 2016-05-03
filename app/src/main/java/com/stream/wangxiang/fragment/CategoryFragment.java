package com.stream.wangxiang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.stream.wangxiang.activity.NewsDetailActivity;
import com.stream.wangxiang.adapter.NewsItemAdapter;
import com.stream.wangxiang.adapter.SubscribeCategoryAdapter;
import com.stream.wangxiang.event.GetCategoryListEvent;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.event.RefreshSubscribeEvent;
import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.net.GetCategoryList;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.view.LoadMoreListView;
import com.stream.wangxiang.vo.CategoryVo;
import com.stream.wangxiang.vo.NewsItem;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 *
 * Created by 张川川 on 2016/4/22.
 */
public class CategoryFragment extends BaseFragment {

    private RecyclerView mSubscribeCategory;

    private List<CategoryVo> mSubscribeCategoryList;
    private int mCheckedPosition = 0;

    private LoadMoreListView mNewsList;
    private PtrClassicFrameLayout mCategoryPtr;
    private List<NewsItem> newsItemsList;
    private NewsItemAdapter newsItemAdapter;

    private int page = 0;

    private final int REFRESH_CODE = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        mSubscribeCategory = (RecyclerView) view.findViewById(R.id.category_subscribed);

        mSubscribeCategoryList = new ArrayList<>();
        newsItemsList = new ArrayList<>();

        view.findViewById(R.id.edit_category_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTabEvent event = new SelectTabEvent(MainFragment.TAB_INDEX_SUBSCRIBE);
                event.setFromTabIndex(MainFragment.TAB_INDEX_CATEGORY);
                EventBus.getDefault().post(event);
            }
        });

        mNewsList = (LoadMoreListView)view.findViewById(R.id.news_list);
        setItemClicked(mNewsList);
        setOnScrollerListener(mNewsList);

        mCategoryPtr = (PtrClassicFrameLayout)view.findViewById(R.id.category_ptr);
        mCategoryPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if(mSubscribeCategoryList.size() == 0){
                    return;
                }
                GetNewsList.getCategoryNewsList(mSubscribeCategoryList.get(mCheckedPosition).getTid(), REFRESH_CODE);
            }
        });

        newsItemAdapter = new NewsItemAdapter(getContext(), R.layout.item_news_digest, newsItemsList);
        mNewsList.setAdapter(newsItemAdapter);

        // 初始化订阅的分类新闻列表
        GetCategoryList.getCagegoryList();
        setOnBusy(true);

        return view;
    }

    /**
     *  设置ListView 的 点击 事件
     * @param mNewsList 要设置的Listview
     */
    private void setItemClicked(final LoadMoreListView mNewsList) {
        mNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(newsItemsList == null){
                    return;
                }
                // 点击的是加载更多
                if(position == newsItemsList.size()){
                    return;
                }

                String postId = newsItemsList.get(position).getPostid();
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

    /**
     *  设置ListView 的 滚动 事件
     * @param mNewsList 要设置的Listview
     */
    private void setOnScrollerListener(final LoadMoreListView mNewsList) {
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
                        if(mSubscribeCategoryList.size() == 0){
                            return;
                        }
                        GetNewsList.getCategoryNewsList(mSubscribeCategoryList.get(mCheckedPosition).getTid(), page*20);
                    }
                }
            }
        });
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


    public void onEventMainThread(GetCategoryListEvent event){
        setOnBusy(false);

        if(event != null){
            if(event.getCategoryVoList() != null){
                List<CategoryVo> mCategoryList = new ArrayList<>();
                for(CategoryVo vo : event.getCategoryVoList()) {
                    if(vo.getTemplate().equals(CategoryVo.CAN_SHOW_CATEGORY)) {
                        mCategoryList.add(vo);
                    }
                }

                Set<String> subscribeSet = SharedPreferenceUtils.getStringSet(SharedPreferenceUtils.KEY_FOR_SUBSCRIBE_CATEGORY, SettingUtils.getDefaultSubscribeStringSet());
                mSubscribeCategoryList.clear();
                for(String s : subscribeSet){
                    mSubscribeCategoryList.add(mCategoryList.get(Integer.valueOf(s)));
                }

                setSubscribeCategoryList(mSubscribeCategoryList);
            }
        }
    }

    public void onEventMainThread(RefreshNewsListEvent event){
        setOnBusy(false);

        if(mCategoryPtr.isRefreshing()){
            newsItemsList.clear();
            mCategoryPtr.refreshComplete();
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
                    newsItemsList.add(item);
                }
            }
        }

        newsItemAdapter.notifyDataSetChanged();


    }

    public void onEventMainThread(RefreshSubscribeEvent event){

        if(SettingUtils.isSettingNew) {
            // 先将原先的数据清除
            newsItemsList.clear();
            // 初始化订阅的分类新闻列表
            GetCategoryList.getCagegoryList();

            setOnBusy(true);
        }
    }

    /**
     *  初始化订阅的分类新闻列表
     */
    private void setSubscribeCategoryList(final List<CategoryVo> categoryList) {

        // 如果存在订阅则请求第一个的数据列表
        if(categoryList.size() >0) {
            // 设置第一个为红色
            categoryList.get(0).setSelected(true);
            mCheckedPosition = 0;
            GetNewsList.getCategoryNewsList(categoryList.get(0).getTid(), REFRESH_CODE);
        }

        final SubscribeCategoryAdapter adapter = new SubscribeCategoryAdapter(categoryList, new SubscribeCategoryAdapter.ClickCategoryListener() {
            @Override
            public void clickThis(int position) {
                if(mCheckedPosition != position){
                    newsItemsList.clear();
                    if(mSubscribeCategoryList.size() == 0){
                        return;
                    }
                    // 请求数据
                    GetNewsList.getCategoryNewsList(categoryList.get(position).getTid(), REFRESH_CODE);
                    categoryList.get(mCheckedPosition).setSelected(false);
                    categoryList.get(position).setSelected(true);
                    mCheckedPosition = position;
                    setOnBusy(true);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSubscribeCategory.setLayoutManager(layoutManager);
        mSubscribeCategory.setAdapter(adapter);


    }


}
