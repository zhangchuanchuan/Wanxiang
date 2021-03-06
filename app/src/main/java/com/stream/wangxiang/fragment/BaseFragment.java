package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.wangxiang.activity.BaseActivity;

/**
 * 基本的Fragment类
 * Created by 张川川 on 2016/4/17.
 */
public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setOnBusy(boolean isBusy){
        this.setOnBusy(isBusy, true);
    }

    public void setOnBusy(boolean isBusy, boolean canCanceled){
        ((BaseActivity)getActivity()).setOnBusy(isBusy, canCanceled);
    }

}
