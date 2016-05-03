package com.stream.wangxiang.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stream.wangxiang.activity.ChooseAreaActivity;
import com.stream.wangxiang.vo.WeatherForecast;
import com.stream.wangxiang.vo.WeatherVo;
import com.stream.wanxiang.R;

import java.util.List;


/**
 * 获取view的类
 * Created by 张川川 on 2016/5/3.
 */
public class ViewUtils {
    /**
     *
     * @param vo 天气的vo
     * @param startContext 开启intent的context
     * @return 一个view
     */
    public static View getWeatherView(WeatherVo vo, final Context startContext){

        if(vo == null){
            return null;
        }
        List<WeatherForecast> list = vo.getForecast();
        if(list == null || list.size() == 0){
            return null;
        }

        WeatherForecast forecast = list.get(0);
        if(forecast == null){
            return null;
        }

        View view = AppUtils.getView(R.layout.view_weather_info);

        TextView date = (TextView) view.findViewById(R.id.weather_date);
        TextView type = (TextView) view.findViewById(R.id.weather_type);
        TextView wind = (TextView) view.findViewById(R.id.weather_wind);
        TextView temp = (TextView) view.findViewById(R.id.weather_temp);
        RelativeLayout bg = (RelativeLayout) view.findViewById(R.id.weather_bg);
        TextView city = (TextView) view.findViewById(R.id.weather_city);

        // 切换城市
        LinearLayout chooseCity = (LinearLayout) view.findViewById(R.id.choose_city);
        chooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(startContext, ChooseAreaActivity.class);
                startContext.startActivity(intent);
            }
        });

        String desp = forecast.getType();

        if(desp.contains("云")){
            bg.setBackgroundResource(R.drawable.ic_weather_cloudy_bg);
        }else if(desp.contains("雪")){
            bg.setBackgroundResource(R.drawable.ic_weather_snow_bg);
        }else if(desp.contains("雨")){
            bg.setBackgroundResource(R.drawable.ic_weather_rain_bg);
        }else{
            bg.setBackgroundResource(R.drawable.ic_weather_sunshine_bg);
        }

        date.setText(forecast.getDate());
        type.setText(forecast.getType()+"   "+vo.getWendu()+"℃");
        wind.setText(forecast.getFengxiang()+"   "+forecast.getFengli());
        temp.setText(forecast.getHigh()+"  "+forecast.getLow());
        city.setText(vo.getCity());
        return view;


    }
}
