package com.stream.wangxiang.vo;

import java.util.List;

/**
 * Created by 张川川 on 2016/4/24.
 */
public class Cities {

    private String category;

    private List<City> cityList;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
