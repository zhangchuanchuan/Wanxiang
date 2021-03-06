package com.stream.wangxiang.view;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stream.wangxiang.utils.AppUtils;
import com.stream.wanxiang.R;

/**
 * 新闻详情使用的textView
 * Created by 张川川 on 2016/4/28.
 */
public class NewsTextView extends TextView {

    public NewsTextView(Spanned content){
        super(AppUtils.context);
        this.setTextColor(AppUtils.getColor(R.color.text_hard_gray_color));
        setTextSize(18);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.setMargins(AppUtils.getDimen(R.dimen.content_text_margin_left), AppUtils.getDimen(R.dimen.content_text_margin_top),
                AppUtils.getDimen(R.dimen.content_text_margin_right), AppUtils.getDimen(R.dimen.content_text_marign_bottom));
        this.setLayoutParams(params);
        this.setText(content);
    }

    public NewsTextView(String content, int size){
        super(AppUtils.context);
        this.setTextColor(AppUtils.getColor(R.color.text_more_gray_color));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.setMargins(AppUtils.getDimen(R.dimen.content_text_margin_left), AppUtils.getDimen(R.dimen.content_text_margin_top),
                AppUtils.getDimen(R.dimen.content_text_margin_right), AppUtils.getDimen(R.dimen.content_text_marign_bottom));
        this.setLayoutParams(params);
        this.setText(content);
        this.setTextSize(size);
    }

    public NewsTextView(Context context) {
        super(context);
    }

    public NewsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
