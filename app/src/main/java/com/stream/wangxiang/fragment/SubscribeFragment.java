package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stream.wanxiang.R;

/**
 * 订阅分类的fragment
 * Created by 张川川 on 2016/5/1.
 */
public class SubscribeFragment extends BaseFragment {



    private int mFromTabIndex;

    private ImageView mCompleteImg;
    private ImageView mCancleImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscribe, container, false);
        return view;
    }


    public int getmFromTabIndex() {
        return mFromTabIndex;
    }

    public void setmFromTabIndex(int mFromTabIndex) {
        this.mFromTabIndex = mFromTabIndex;
    }

}
