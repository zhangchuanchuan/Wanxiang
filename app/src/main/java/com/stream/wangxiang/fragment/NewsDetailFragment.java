package com.stream.wangxiang.fragment;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stream.wangxiang.event.GetNewsDetailEvent;
import com.stream.wangxiang.net.GetNewsList;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.view.NewsEcTextView;
import com.stream.wangxiang.view.NewsSimpleDraweeView;
import com.stream.wangxiang.view.NewsSourceTextView;
import com.stream.wangxiang.view.NewsTextView;
import com.stream.wangxiang.view.NewsTitleTextView;
import com.stream.wangxiang.vo.NewsComponent;
import com.stream.wangxiang.vo.NewsDetailVo;
import com.stream.wangxiang.vo.NewsImg;
import com.stream.wanxiang.R;

import org.xml.sax.XMLReader;

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

        if(vo.getTitle().length()>15){
            mNewsTitle.setText(vo.getTitle().substring(0, 14)+"...");
        }else {
            mNewsTitle.setText(vo.getTitle());
        }

        //  添加title
        NewsTitleTextView titleTextView = new NewsTitleTextView(vo.getTitle());
        mNewsContent.addView(titleTextView);

        // 添加源和时间
        NewsSourceTextView sourceTextView = new NewsSourceTextView(vo.getSource()+"  "+vo.getPtime());
        mNewsContent.addView(sourceTextView);

        // 添加正文
        List<NewsImg> imgList = vo.getImg();
        String body = vo.getBody();
        List<Spanned> list = StringUtils.parseBodyString(body);
        Log.d("zcc", list.toString());
        int index = 0;
        for(Spanned s : list){
            NewsTextView newsTextView = new NewsTextView(s);
            mNewsContent.addView(newsTextView);
            if(index < imgList.size()){
                NewsImg imgItem = imgList.get(index);
                NewsSimpleDraweeView newsSimpleDraweeView = new NewsSimpleDraweeView(imgItem);
                mNewsContent.addView(newsSimpleDraweeView);

                if(!StringUtils.isNullOrEmpty(imgItem.getAlt())){
                    NewsTextView ntv = new NewsTextView(imgItem.getAlt(), 12);
                    mNewsContent.addView(ntv);
                }
                index++;
            }
        }

        // 添加编辑
        if(!StringUtils.isNullOrEmpty(vo.getEc())) {
            NewsEcTextView ecTextView = new NewsEcTextView(vo.getEc());
            mNewsContent.addView(ecTextView);
        }

    }
}
