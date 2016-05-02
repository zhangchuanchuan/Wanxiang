package com.stream.wangxiang.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.stream.wangxiang.vo.CategoryVo;
import com.stream.wanxiang.R;
import com.stream.wanxiang.databinding.ItemCategoryBinding;

import java.util.List;

/**
 * 所有分类的适配器
 * Created by 张川川 on 2016/5/2.
 */
public class CategoryAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private List<CategoryVo> mDataList;

    public CategoryAdapter(Context context, List<CategoryVo> dataList){
        mInflater = LayoutInflater.from(context);
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
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_category, null);
        }
        CategoryVo vo = mDataList.get(position);
        ItemCategoryBinding itemCategoryBinding = DataBindingUtil.bind(convertView);
        itemCategoryBinding.setCategoryItem(vo);

        return convertView;
    }
}
