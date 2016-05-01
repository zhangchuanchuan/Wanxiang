package com.stream.wangxiang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.vo.CategoryVo;
import com.stream.wanxiang.R;

import java.util.List;

/**
 *
 * Created by 张川川 on 2016/5/1.
 */
public class SubscribeCategoryAdapter extends RecyclerView.Adapter<SubscribeCategoryAdapter.CategoryItemViewHolder> {


    private List<CategoryVo> mCateList ;

    private ClickCategoryListener mListener;

    public SubscribeCategoryAdapter(List<CategoryVo> cateList, ClickCategoryListener listener){
        mCateList = cateList;
        mListener = listener;
    }

    @Override
    public CategoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(AppUtils.context).inflate(R.layout.item_subscribe_category, parent, false));
    }

    @Override
    public void onBindViewHolder(final CategoryItemViewHolder holder, int position) {
        CategoryVo cateVo = mCateList.get(position);
        holder.textView.setText(cateVo.getName());

        if(cateVo.isChcked()){
            // 设置背景颜色
            holder.textView.setTextColor(AppUtils.getColor(R.color.text_brick_red_color));
            holder.view.setVisibility(View.VISIBLE);

        }else{
            holder.textView.setTextColor(AppUtils.getColor(R.color.gray));
            holder.view.setVisibility(View.GONE);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickThis(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCateList.size();
    }

    class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        private View view;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.category_item);
            view = itemView.findViewById(R.id.base_line);
        }
    }

    public interface ClickCategoryListener{
        void clickThis(int position);

    }

}
