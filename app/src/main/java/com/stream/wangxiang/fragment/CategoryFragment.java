package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stream.wangxiang.adapter.SubscribeCategoryAdapter;
import com.stream.wangxiang.event.GetCategoryListEvent;
import com.stream.wangxiang.event.RefreshNewsListEvent;
import com.stream.wangxiang.event.RefreshSubscribeEvent;
import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.net.GetCategoryList;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.vo.CategoryVo;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.greenrobot.event.EventBus;

/**
 *
 * Created by 张川川 on 2016/4/22.
 */
public class CategoryFragment extends BaseFragment {

    private RecyclerView mSubscribeCategory;

    private int mCheckedPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        mSubscribeCategory = (RecyclerView) view.findViewById(R.id.category_subscribed);

        view.findViewById(R.id.edit_category_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTabEvent event = new SelectTabEvent(MainFragment.TAB_INDEX_SUBSCRIBE);
                event.setFromTabIndex(MainFragment.TAB_INDEX_CATEGORY);
                EventBus.getDefault().post(event);
            }
        });

        // 初始化订阅的分类新闻列表
        GetCategoryList.getCagegoryList();
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

                Set<String> subscribeSet = SharedPreferenceUtils.getStringSet(SharedPreferenceUtils.KEY_FOR_SUBSCRIBE_CATEGORY);
                List<CategoryVo> mSubscribeList = new ArrayList<>();
                for(String s : subscribeSet){
                    mSubscribeList.add(mCategoryList.get(Integer.valueOf(s)));
                }

                setSubscribeCategoryList(mSubscribeList);
            }
        }
    }

    public void onEventMainThread(RefreshNewsListEvent event){
        setOnBusy(false);
    }

    public void onEventMainThread(RefreshSubscribeEvent event){
        // 初始化订阅的分类新闻列表
        GetCategoryList.getCagegoryList();
        setOnBusy(true);
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
            GetNewsList.getCategoryNewList(categoryList.get(0).getTid());
        }

        final SubscribeCategoryAdapter adapter = new SubscribeCategoryAdapter(categoryList, new SubscribeCategoryAdapter.ClickCategoryListener() {
            @Override
            public void clickThis(int position) {
                AppUtils.showShortToast(position+"");
                if(mCheckedPosition != position){
                    categoryList.get(mCheckedPosition).setSelected(false);
                    categoryList.get(position).setSelected(true);
                    mCheckedPosition = position;
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSubscribeCategory.setLayoutManager(layoutManager);
        mSubscribeCategory.setAdapter(adapter);


    }


}
