package com.stream.wangxiang.vo;

import java.util.List;

/**
 * 天气的对象类
 * Created by 张川川 on 2016/5/3.
 */
public class WeatherVo {
    private String wendu;

    private String ganmao;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public List<WeatherForecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherForecast> forecast) {
        this.forecast = forecast;
    }

    private List<WeatherForecast> forecast;
}
