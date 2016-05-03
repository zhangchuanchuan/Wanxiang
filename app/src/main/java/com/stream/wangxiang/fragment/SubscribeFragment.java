package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.stream.wangxiang.adapter.CategoryAdapter;
import com.stream.wangxiang.event.GetCategoryListEvent;
import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.net.GetCategoryList;
import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.view.LoadMoreListView;
import com.stream.wangxiang.vo.CategoryVo;
import com.stream.wanxiang.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 订阅分类的fragment
 * Created by 张川川 on 2016/5/1.
 */
public class SubscribeFragment extends BaseFragment implements View.OnClickListener{



    private int mFromTabIndex;

    private ImageView mCompleteImg;
    private ImageView mCancelImg;

    private ListView mCategoryListView;
    private PtrClassicFrameLayout mPtrFrameLayout;

    private List<CategoryVo> mCategoryList;
    private CategoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscribe, container, false);
        mCompleteImg = (ImageView) view.findViewById(R.id.subscribe_complete);
        mCancelImg = (ImageView) view.findViewById(R.id.subscribe_cancel);
        mCompleteImg.setOnClickListener(this);
        mCancelImg.setOnClickListener(this);

        mCategoryListView = (ListView)view.findViewById(R.id.category_list);

        mPtrFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.category_ptr);
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getCategoryList();
            }
        });

        mCategoryList = new ArrayList<>();
        adapter = new CategoryAdapter(getContext(), mCategoryList);
        mCategoryListView.setAdapter(adapter);

        mCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryVo tempVo = mCategoryList.get(position);
                if(tempVo.isChecked()){
                    tempVo.setChecked(false);
                }else{
                    tempVo.setChecked(true);
                }
                adapter.notifyDataSetChanged();
            }
        });

        setOnBusy(true);
        getCategoryList();

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

    private void getCategoryList() {
        GetCategoryList.getCagegoryList();
    }


    public int getmFromTabIndex() {
        return mFromTabIndex;
    }

    public void setmFromTabIndex(int mFromTabIndex) {
        this.mFromTabIndex = mFromTabIndex;
    }


    public void onEventMainThread(GetCategoryListEvent event){

        setOnBusy(false);

        if(mPtrFrameLayout.getHeader().isShown()){
            mPtrFrameLayout.refreshComplete();
        }

        if(event != null){
            if(event.getCategoryVoList() != null){
                mCategoryList.clear();
                for(CategoryVo vo : event.getCategoryVoList()) {
                    if(vo.getTemplate().equals(CategoryVo.CAN_SHOW_CATEGORY)) {
                        mCategoryList.add(vo);
                    }
                }

                Set<String> subscribeSet = SharedPreferenceUtils.getStringSet(SharedPreferenceUtils.KEY_FOR_SUBSCRIBE_CATEGORY, SettingUtils.getDefaultSubscribeStringSet());
                for(String s : subscribeSet){
                    mCategoryList.get(Integer.valueOf(s)).setChecked(true);
                }

                adapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subscribe_complete:

                Set<String> subscribeSet = new TreeSet<>();
                for(int i=0; i<mCategoryList.size() ; i++){

                    if(mCategoryList.get(i).isChecked()){
                        subscribeSet.add(String.valueOf(i));
                    }
                }
                SharedPreferenceUtils.putStringSet(SharedPreferenceUtils.KEY_FOR_SUBSCRIBE_CATEGORY, subscribeSet);

                SettingUtils.isSettingNew = true;
                SelectTabEvent event = new SelectTabEvent(this.mFromTabIndex);
                EventBus.getDefault().post(event);
                break;
            case R.id.subscribe_cancel:
                EventBus.getDefault().post(new SelectTabEvent(this.mFromTabIndex));
                break;
        }
    }
}
