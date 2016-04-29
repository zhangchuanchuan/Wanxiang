package com.stream.wangxiang.view;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stream.wangxiang.utils.AppUtils;
import com.stream.wanxiang.R;

/**
 * Created by 张川川 on 2016/4/29.
 */
public class NewsEcTextView extends TextView {
    public NewsEcTextView(Context context) {
        super(context);
    }

    public NewsEcTextView(String ec){
        this(AppUtils.context);
        this.setTextColor(AppUtils.getColor(R.color.text_hint_color));
        setTextSize(14);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.setMargins(AppUtils.getDimen(R.dimen.content_ec_margin_left), AppUtils.getDimen(R.dimen.content_ec_margin_top),
                AppUtils.getDimen(R.dimen.content_ec_margin_right), AppUtils.getDimen(R.dimen.content_ec_margin_bottom));
        this.setGravity(Gravity.RIGHT);
        this.setLayoutParams(params);
        this.setText("[责任编辑："+ec+"]");
    }

}
