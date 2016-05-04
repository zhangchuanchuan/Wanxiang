package com.stream.wangxiang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.a.a.I;
import com.stream.wangxiang.event.UpdateLocalCityEvent;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.vo.Cities;
import com.stream.wangxiang.vo.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

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
     *  通过这个方法将数据和辅助的letterview加进去
     * @param context context
     * @param letterListView letter
     * @param citiesList data
     */
    public void setLetterListView(Context context, LetterListView letterListView, List<Cities> citiesList) {
        mLetterListView = letterListView;
        final List<String> strList = new ArrayList<>();
        indexMap = new HashMap<>();
        indexMap.put("#", 0);
        int index = 0;
        for (Cities cities : citiesList) {
            indexMap.put(cities.getCategory(), index);
            index = index + cities.getCityList().size() + 1;

            strList.add(cities.getCategory());
            for(City city : cities.getCityList()){
                strList.add(city.getC());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, strList);
        this.setAdapter(adapter);
        final ListView listView = this;
        letterListView.setOnTouchingLetterChangedListener(new LetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String var1) {
                while(indexMap.get(var1) == null){
                    var1 = String.valueOf((char)(var1.charAt(0) - 1));
                }

                listView.setSelection(indexMap.get(var1));
            }
        });

        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(strList.get(position).length() != 1){
                    // 点击的是城市
                    SharedPreferenceUtils.putString(SharedPreferenceUtils.KEY_FOR_LOCAL_CITY, strList.get(position));
                    EventBus.getDefault().post(new UpdateLocalCityEvent());
                }
            }
        });
    }

}
