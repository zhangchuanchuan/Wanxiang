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
import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.vo.CategoryVo;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 *
 * Created by 张川川 on 2016/4/22.
 */
public class CategoryFragment extends BaseFragment {

    private RecyclerView mSubcribeCategory;

    private int mCheckedPosition = 0;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        mSubcribeCategory = (RecyclerView) view.findViewById(R.id.category_subscribed);

        view.findViewById(R.id.edit_category_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTabEvent event = new SelectTabEvent(MainFragment.TAB_INDEX_SUBSCRIBE);
                event.setFromTabIndex(MainFragment.TAB_INDEX_CATEGORY);
                EventBus.getDefault().post(event);
            }
        });

        // 初始化订阅的分类新闻列表
        setSubscribeCategoryList();

        return view;
    }


    /**
     *  初始化订阅的分类新闻列表
     */
    private void setSubscribeCategoryList() {

        final List<CategoryVo> mCateList = new ArrayList<>();

        CategoryVo vo1 = new CategoryVo();
        vo1.setName("分类1");
        vo1.setChcked(true);

        CategoryVo vo2 = new CategoryVo();
        vo2.setName("分类2");

        mCateList.add(vo1);
        mCateList.add(vo2);
        mCateList.add(vo1);
        mCateList.add(vo2);
        mCateList.add(vo1);
        mCateList.add(vo2);
        mCateList.add(vo1);
        mCateList.add(vo2);
        mCateList.add(vo1);
        mCateList.add(vo2);

        final SubscribeCategoryAdapter adapter = new SubscribeCategoryAdapter(mCateList, new SubscribeCategoryAdapter.ClickCategoryListener() {
            @Override
            public void clickThis(int position) {
                AppUtils.showShortToast(position+"");
                if(mCheckedPosition != position){
                    mCateList.get(mCheckedPosition).setChcked(false);
                    mCateList.get(position).setChcked(true);
                    mCheckedPosition = position;
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSubcribeCategory.setLayoutManager(layoutManager);
        mSubcribeCategory.setAdapter(adapter);
    }


}
