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
        String[] dimens = size.split("\\*");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Integer.valueOf(dimens[0]),
                (Integer.valueOf(dimens[1])));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        this.setLayoutParams(params);
        this.setImageURI(Uri.parse(img.getSrc()));

    }

}
