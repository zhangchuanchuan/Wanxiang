package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stream.wanxiang.R;

/**
 * 新闻详情的fragment
 * Created by 张川川 on 2016/4/22.
 */
public class NewsDetailFragment extends BaseFragment implements View.OnClickListener {

    private String mPostId;

    private ImageView mBackImg;

    public static NewsDetailFragment newInstance(String postId) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.mPostId = postId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news_detail, null);
        mBackImg = (ImageView) view.findViewById(R.id.img_back);
        mBackImg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                getActivity().finish();
                break;
        }
    }
}
