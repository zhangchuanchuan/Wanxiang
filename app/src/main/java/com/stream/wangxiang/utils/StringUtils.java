package com.stream.wangxiang.utils;

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
    public static List<NewsComponent> parseBodyString(String body){

        body = body.replace("<!--", "<annotate>");
        body = body.replace("-->", "</annotate>");
        List<NewsComponent> list = new ArrayList<>();
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(new StringReader(body));
            int eventType = parser.getEventType();

            while(eventType!= XmlPullParser.END_DOCUMENT){
                String nodeName = parser.getName();
                if(nodeName != null) {
                    Log.d("zcc", parser.nextText());
                    switch (nodeName) {
                        case "p":
                            NewsComponent textComponent = new NewsComponent();
                            textComponent.setType(NewsComponent.TYPE_TEXT);
                            textComponent.setData(parser.nextText());
                            list.add(textComponent);
                            break;
                        case "annotate":
                            NewsComponent annotateComponent = new NewsComponent();
                            annotateComponent.setType(NewsComponent.TYPE_ANNOTATE);
                            annotateComponent.setData(parser.nextText());
                            list.add(annotateComponent);
                            break;
                        case "b":
                            NewsComponent bComponent = new NewsComponent();
                            bComponent.setType(NewsComponent.TYPE_BOLD);
                            bComponent.setData(parser.nextText());
                            list.add(bComponent);
                            break;
                        case "strong":
                            NewsComponent strongComponent = new NewsComponent();
                            strongComponent.setType(NewsComponent.TYPE_STRONG);
                            strongComponent.setData(parser.nextText());
                            list.add(strongComponent);
                            break;
                        default:
                            NewsComponent othersComponent = new NewsComponent();
                            othersComponent.setType(NewsComponent.TYPE_OTHERS);
                            othersComponent.setData(parser.nextText());
                            list.add(othersComponent);
                            break;
                    }
                }
                //下一个结点
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

}
