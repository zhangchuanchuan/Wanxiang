package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.WeatherVo;

/**
 * Created by 张川川 on 2016/5/3.
 */
public class GetWeatherInfoEvent extends BaseEvent {
    private WeatherVo weatherVo;

    public WeatherVo getWeatherVo() {
        return weatherVo;
    }

    public void setWeatherVo(WeatherVo weatherVo) {
        this.weatherVo = weatherVo;
    }
}
