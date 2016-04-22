package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wanxiang.R;

import de.greenrobot.event.EventBus;

/**
 * 主要的地方
 * Created by 张川川 on 2016/4/21.
 */
public class MainInterfaceFragment extends BaseFragment implements View.OnClickListener{

    public static final int TAB_INDEX_HOME = 0;
    public static final int TAB_INDEX_CATEGORY = 1;
    public static final int TAB_IDNEX_SUBSCRIBE = 2;
    public static final int TAB_INDEX_LOCAL = 3;
    public static final int TAB_INDEX_MYSELF = 4;

    private ImageView mIconHome;
    private ImageView mIconCategory;
    private ImageView mIconSubscribe;
    private ImageView mIconLocal;
    private ImageView mIconMyself;

    private int selectTabIndex = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_interface, container, false);
        mIconHome = ((ImageView) view.findViewById(R.id.icon_home));
        mIconCategory = ((ImageView) view.findViewById(R.id.icon_category));
        mIconSubscribe = ((ImageView) view.findViewById(R.id.icon_subscribe));
        mIconLocal = ((ImageView) view.findViewById(R.id.icon_local));
        mIconMyself = ((ImageView) view.findViewById(R.id.icon_myself));
        mIconHome.setOnClickListener(this);
        mIconCategory.setOnClickListener(this);
        mIconSubscribe.setOnClickListener(this);
        mIconLocal.setOnClickListener(this);
        mIconMyself.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_home:
                setUnSelectGray();
                mIconHome.setImageResource(R.drawable.home_icon_pressed);
                selectTabIndex = 0;

                SelectTabEvent homeTabEvent = new SelectTabEvent();
                homeTabEvent.setTabIndex(TAB_INDEX_HOME);
                EventBus.getDefault().post(homeTabEvent);
                break;
            case R.id.icon_category:
                setUnSelectGray();
                mIconCategory.setImageResource(R.drawable.category_pressed);
                selectTabIndex = 1;

                SelectTabEvent cateTabEvent = new SelectTabEvent();
                cateTabEvent.setTabIndex(TAB_INDEX_CATEGORY);
                EventBus.getDefault().post(cateTabEvent);
                break;
            case R.id.icon_subscribe:
                SelectTabEvent subTabEvent = new SelectTabEvent();
                subTabEvent.setTabIndex(TAB_IDNEX_SUBSCRIBE);
                EventBus.getDefault().post(subTabEvent);
                break;
            case R.id.icon_local:
                setUnSelectGray();
                mIconLocal.setImageResource(R.drawable.home_location_pressed);
                selectTabIndex = 2;

                SelectTabEvent localTabEvent = new SelectTabEvent();
                localTabEvent.setTabIndex(TAB_INDEX_LOCAL);
                EventBus.getDefault().post(localTabEvent);
                break;
            case R.id.icon_myself:
                setUnSelectGray();
                mIconMyself.setImageResource(R.drawable.my_icon_pressed);
                selectTabIndex = 3;

                SelectTabEvent myTabEvent = new SelectTabEvent();
                myTabEvent.setTabIndex(TAB_INDEX_MYSELF);
                EventBus.getDefault().post(myTabEvent);
                break;
        }
    }

    /**
     *  未点击重置
     */
    private void setUnSelectGray(){
        switch (selectTabIndex){
            case 0:
                mIconHome.setImageResource(R.drawable.home_icon_normal);
                break;
            case 1:
                mIconCategory.setImageResource(R.drawable.category_normal);
                break;
            case 2:
                mIconLocal.setImageResource(R.drawable.home_location_normal);
                break;
            case 3:
                mIconMyself.setImageResource(R.drawable.my_icon_normal);
                break;
        }
    }

}
