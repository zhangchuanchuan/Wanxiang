package com.stream.wangxiang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.stream.wangxiang.utils.AppUtils;
import com.stream.wanxiang.R;

/**
 * 可以加载更多的listview
 * Created by 张川川 on 2016/4/26.
 */
public class LoadMoreListView extends ListView {

    private View mLoadMoreView;

    public boolean isFooterIsShow() {
        return footerIsShow;
    }

    public void setFooterIsShow(boolean footerIsShow) {
        this.footerIsShow = footerIsShow;
    }

    private boolean footerIsShow = false;

    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFooterShow(){
        if(mLoadMoreView == null){
            mLoadMoreView = AppUtils.getView(R.layout.load_more_bottom);
        }
        if(!footerIsShow) {
            this.addFooterView(mLoadMoreView);
            footerIsShow = true;
        }
    }

    public void setFooterGone(){
        if(mLoadMoreView != null && footerIsShow){
            this.removeFooterView(mLoadMoreView);
            footerIsShow = false;
        }
    }

}
