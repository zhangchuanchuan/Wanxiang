package com.stream.wangxiang.view;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stream.wangxiang.utils.AppUtils;
import com.stream.wanxiang.R;

/**
 * Created by 张川川 on 2016/4/29.
 */
public class NewsTitleTextView extends TextView {
    public NewsTitleTextView(Context context) {
        super(context);
    }

    public NewsTitleTextView(String title){
        this(AppUtils.context);
        this.setTextColor(AppUtils.getColor(R.color.text_hard_gray_color));
        setTextSize(20);
        setTypeface(Typeface.DEFAULT_BOLD);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.setMargins(AppUtils.getDimen(R.dimen.content_title_margin_left), AppUtils.getDimen(R.dimen.content_title_margin_top),
                AppUtils.getDimen(R.dimen.content_title_margin_right), AppUtils.getDimen(R.dimen.content_title_margin_bottom));
        this.setLayoutParams(params);
        this.setText(title);
    }

}
