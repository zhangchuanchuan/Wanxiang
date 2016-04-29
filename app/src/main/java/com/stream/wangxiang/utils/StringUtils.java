package com.stream.wangxiang.utils;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.stream.wangxiang.vo.NewsComponent;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串相关的工具类
 * Created by 张川川 on 2016/4/24.
 */
public class StringUtils {

    /**
     *  字符串是否为空
     * @param string 传入的字符串
     * @return 是空或者空指针返回ture
     */
    public static boolean isNullOrEmpty(String string){
        if(string == null){
            return true;
        }

        if(string.equals("")){
            return true;
        }
        return false;

    }

    /**
     *  获取abc字符串大写字母的列表
     */
    public static List<String> getABCStringArray(){

        List<String> list = new ArrayList<>();
        for(int i=0; i<26; i++){
            int c = 'A' + i;
            char a = (char) c;
            if(a != 'I' && a != 'O' && a != 'U' && a !='V') {
                list.add(String.valueOf(a));
            }
        }
        return list;
    }

    /**
     *  解析body字符串
     * @param body 新闻body
     * @return 分段的list
     */
    public static List<Spanned> parseBodyString(String body){
        List<Spanned> list = new ArrayList<>();

        String[] strs = body.split("<!--IMG#[0-9]*-->");
        for(String s : strs) {
            list.add(Html.fromHtml(s));
        }
        return list;
    }

    public static void parseBody(String body){
    }

}
