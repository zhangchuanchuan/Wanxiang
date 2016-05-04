package com.stream.wangxiang.view;

import android.content.Context;
import android.net.Uri;
import android.util.Size;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.DimenUtils;
import com.stream.wangxiang.vo.NewsImg;
import com.stream.wanxiang.R;

import java.net.URI;

/**
 *
 * Created by 张川川 on 2016/4/28.
 */
public class NewsSimpleDraweeView extends SimpleDraweeView {
    public NewsSimpleDraweeView(Context context) {
        super(context);
    }

    public NewsSimpleDraweeView(NewsImg img){
        super(AppUtils.context);
        String size = img.getPixel();
        if(size == null){
            return;
        }
        String[] dimens = size.split("\\*");

        int windowWidth = DimenUtils.getDisplayWidth(AppUtils.context) - 64;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(windowWidth,
                windowWidth*(Integer.valueOf(dimens[1]))/(Integer.valueOf(dimens[0])));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.setMargins(AppUtils.getDimen(R.dimen.drawee_margin_left), AppUtils.getDimen(R.dimen.drawee_margin_top),
                AppUtils.getDimen(R.dimen.drawee_margin_right), AppUtils.getDimen(R.dimen.drawee_margin_top));
        this.setLayoutParams(params);
        this.setImageURI(Uri.parse(img.getSrc()));

    }

}
