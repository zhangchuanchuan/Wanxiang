package com.stream.wangxiang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.stream.wangxiang.vo.NewsItem;

import java.util.List;

/**
 * Created by 张川川 on 2016/4/25.
 */
public class NewsItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private int mResourceId;
    private List<NewsItem> mDataList;

    public NewsItemAdapter(Context context, int resourceId, List<NewsItem> dataList){
        mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    class NewsItemViewHolder{

    }

}
