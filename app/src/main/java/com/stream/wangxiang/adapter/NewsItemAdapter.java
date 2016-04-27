package com.stream.wangxiang.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.tool.Binding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.stream.wangxiang.activity.NewsDetailActivity;
import com.stream.wangxiang.vo.NewsItem;
import com.stream.wanxiang.databinding.ItemNewsDigestBinding;

import java.util.List;

/**
 * Created by 张川川 on 2016/4/25.
 */
public class NewsItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int mResourceId;
    private List<NewsItem> mDataList;

    public NewsItemAdapter(Context context, int resourceId, List<NewsItem> dataList){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mResourceId = resourceId;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(mResourceId, null);
        }
        final NewsItem item = mDataList.get(position);
        ItemNewsDigestBinding itemNewsDigestBinding = DataBindingUtil.bind(convertView);
        itemNewsDigestBinding.setNewItem(item);
        return convertView;
    }


}
