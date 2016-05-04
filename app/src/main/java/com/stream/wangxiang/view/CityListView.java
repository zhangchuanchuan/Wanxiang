package com.stream.wangxiang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.stream.wangxiang.vo.Cities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 城市列表的ListView
 * Created by 张川川 on 2016/5/4.
 */
public class CityListView extends ListView {

    // 左侧的index 字母
    private LetterListView mLetterListView;

    // 存储位置的
    Map<String, Integer> indexMap;



    public CityListView(Context context) {
        super(context);
    }

    public CityListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *  外界通过这个构造
     * @param context context
     * @param letterListView letterListView
     */
    public CityListView(Context context, LetterListView letterListView, List<Cities> citiesList){
        this(context);
        mLetterListView = letterListView;

        List<String> strList = new ArrayList<>();
        indexMap = new HashMap<>();
        int index = 0;
        for(Cities cities : citiesList){

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, strList);
        this.setAdapter(adapter);
    }




}
