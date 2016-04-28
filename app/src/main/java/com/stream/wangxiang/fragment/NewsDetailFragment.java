package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stream.wangxiang.event.GetNewsDetailEvent;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.view.NewsSimpleDraweeView;
import com.stream.wangxiang.view.NewsTextView;
import com.stream.wangxiang.vo.NewsDetailVo;
import com.stream.wangxiang.vo.NewsImg;
import com.stream.wanxiang.R;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 新闻详情的fragment
 * Created by 张川川 on 2016/4/22.
 */
public class NewsDetailFragment extends BaseFragment implements View.OnClickListener {

    private String mPostId;

    private ImageView mBackImg;
    private TextView mNewsTitle;
    private LinearLayout mNewsContent;

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
        mNewsContent = (LinearLayout) view.findViewById(R.id.news_detail_content);
        mNewsTitle = (TextView) view.findViewById(R.id.news_detail_title);
        setOnBusy(true);
        GetNewsList.getNewsDetail(mPostId);
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

    public void onEventMainThread(GetNewsDetailEvent event){
        setOnBusy(false);
        if(event == null){
            return;
        }

        NewsDetailVo vo = event.getDetailVo();
        if(vo == null){
            return;
        }

        setNewsDetail(vo);



    }

    /**
     *  设置新闻详情的显示
     * @param vo 新闻详情信息
     */
    private void setNewsDetail(NewsDetailVo vo) {
        mNewsTitle.setText(vo.getTitle());

        String body = vo.getBody();
        String[] strs = body.split("<p>");
        List<NewsImg> imgList = vo.getImg();

        for(String s : strs){
            if(s.contains("<!--IMG")){
                NewsSimpleDraweeView nsdv = new NewsSimpleDraweeView(imgList.get(0));
                mNewsContent.addView(nsdv);

            }else {
                if(s.length()>4) {
                    s = s.substring(0, s.length() - 4);
                    NewsTextView tv = new NewsTextView(s);
                    mNewsContent.addView(tv);
                }
            }




        }

    }
}
