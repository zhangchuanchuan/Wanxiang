package com.stream.wangxiang.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 全局工具类，用于获取资源文件
 * Created by 张川川 on 2016/4/17.
 */
public class AppUtils {
    public static Context context;

    public static void setContext(Context mContext){
        if(context == null){
            context = mContext;
        }
    }

    public static String getString(int resId) {
        return context.getResources().getString(resId);
    }

    public static String getString(int resId, Object... formatArgs) {
        return context.getResources().getString(resId, formatArgs);
    }

    public static int getColor(int id) {
        return context.getResources().getColor(id);
    }

    public static Drawable getDrawable(int id) {
        return context.getResources().getDrawable(id);
    }

    public static float getDimension(int id) {
        return context.getResources().getDimension(id);
    }

    public static int getDimen(int id){
        return (int)context.getResources().getDimension(id);
    }

    public static void showShortToast(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static View getView(int resourceId){
        return LayoutInflater.from(context).inflate(resourceId, null);
    }


}
