package com.stream.wangxiang.utils;

import android.app.Fragment;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 *
 * Created by 张川川 on 2016/4/28.
 */
public class DimenUtils {


    /** @deprecated */
    @Deprecated
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    /** @deprecated */
    @Deprecated
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int px2dip(float pxValue) {
        float scale = AppUtils.context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }


    public static int dip2px(float dipValue) {
        float scale = AppUtils.context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(spValue * fontScale + 0.5F);
    }

    public static int getStatusBarHeight(Context ctx) {
        int statusBarHeight = 0;

        try {
            Class e = Class.forName("com.android.internal.R$dimen");
            Object object = e.newInstance();
            Field field = e.getField("status_bar_height");
            int height = Integer.parseInt(field.get(object).toString());
            statusBarHeight = ctx.getResources().getDimensionPixelSize(height);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return statusBarHeight;
    }

    public static int getDisplayWidth(Context ctx) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager winManager = (WindowManager)ctx.getSystemService("window");
        winManager.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    public static int getDisplayHeight(Context ctx) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager winManager = (WindowManager)ctx.getSystemService("window");
        winManager.getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static float range(int percentage, float start, float end) {
        return (end - start) * (float)percentage / 100.0F + start;
    }

    public static float getDimension(Fragment fragment, int id) {
        return fragment.getResources().getDimension(id);
    }

    public static float getDimension(int id) {
        return AppUtils.context.getResources().getDimension(id);
    }
}
