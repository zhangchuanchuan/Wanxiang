package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.wanxiang.R;

/**
 *
 * Created by 张川川 on 2016/4/22.
 */
public class CategoryFragment extends BaseFragment {

    private RecyclerView mSubcribeCategory;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        mSubcribeCategory = (RecyclerView) view.findViewById(R.id.category_subscribed);
        return view;
    }


}
